package gov.nist.csd.pm.blockmatrix;

import gov.nist.blockmatrixtimestamped.BlockTensor;
import gov.nist.csd.pm.exceptions.PMException;

import java.io.IOException;
import java.util.*;

public class UsersBlockMatrix {

    private BlockTensor bm;
    private Map<String, Integer> usersMap;

    public UsersBlockMatrix(int numUsers) {
        bm = new BlockTensor(numUsers);
        usersMap = new HashMap<>();
    }

    public synchronized void addUser(String user, Collection<String> attributes) throws PMException {
        if (usersMap.containsKey(user)) {
            throw new PMException("user " + user + " already exists");
        }

        try {
            int index = bm.add(new UserBlock(user, attributes).toByteArray());
            usersMap.put(user, index);
        } catch (IOException e) {
            throw new PMException("error adding user: " + e.getMessage());
        }
    }

    public synchronized void removeUser(String user) {
        int index = usersMap.get(user);
        bm.erase(index);
        usersMap.remove(user);
    }

    public synchronized UserBlock getUser(String user) throws PMException {
        int index = usersMap.get(user);
        if (index < 0) {
            throw new PMException("user " + user + " does not exist");
        }
        return new UserBlock(bm.getData(index));
    }

    public synchronized void addAttributes(String user, String ... attributes) throws PMException {
        int index = usersMap.get(user);
        if (index < 0) {
            addUser(user, Arrays.asList(attributes));
        } else {
            removeUser(user);
            addUser(user, Arrays.asList(attributes));
        }
    }
}
