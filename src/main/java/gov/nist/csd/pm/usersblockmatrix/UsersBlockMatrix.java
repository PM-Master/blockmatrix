package gov.nist.csd.pm.usersblockmatrix;

import gov.nist.csd.pm.blockmatrix.BlockTensor;

import java.io.IOException;
import java.util.*;

public class UsersBlockMatrix {

    private BlockTensor bm;
    private Map<String, Integer> usersMap;

    public UsersBlockMatrix(int numUsers) {
        bm = new BlockTensor(numUsers);
        usersMap = new HashMap<>();
    }

    public synchronized void addUser(String user, String ... attributes) throws BlockMatrixException {
        if (usersMap.containsKey(user)) {
            updateUser(user, attributes);
        }

        try {
            int index = bm.add(new UserBlock(user, Arrays.asList(attributes)).toByteArray());
            usersMap.put(user, index);
        } catch (IOException e) {
            throw new BlockMatrixException("error adding user: " + e.getMessage());
        }
    }

    public List<UserBlock> getUsers() throws BlockMatrixException {
        List<UserBlock> blocks = new ArrayList<>();
        for (String user : usersMap.keySet()) {
            blocks.add(getUser(user));
        }

        return blocks;
    }

    public synchronized void removeUser(String user) {
        int index = usersMap.get(user);
        bm.erase(index);
        usersMap.remove(user);
    }

    public synchronized UserBlock getUser(String user) throws BlockMatrixException {
        Integer index = usersMap.get(user);
        if (index == null) {
            throw new BlockMatrixException("user " + user + " does not exist");
        }
        byte[] data = bm.getData(index);
        return new UserBlock(data);
    }

    public synchronized void updateUser(String user, String ... attributes) throws BlockMatrixException {
        int index = usersMap.get(user);
        if (index < 0) {
            addUser(user, attributes);
        } else {
            removeUser(user);
            addUser(user, attributes);
        }
    }
}
