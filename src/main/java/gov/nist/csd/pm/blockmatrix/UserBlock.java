package gov.nist.csd.pm.blockmatrix;

import gov.nist.csd.pm.exceptions.PMException;

import java.io.*;
import java.util.Collection;

public class UserBlock implements Serializable {
    private String username;
    private Collection<String> attributes;

    public UserBlock(String username, Collection<String> attributes) {
        this.username = username;
        this.attributes = attributes;
    }

    public UserBlock(byte[] userData) throws PMException {
        ByteArrayInputStream bis = new ByteArrayInputStream(userData);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            UserBlock ub = (UserBlock)o;
            this.setUsername(ub.getUsername());
            this.setAttributes(ub.getAttributes());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new PMException("error extracting user from data: " + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Collection<String> attributes) {
        this.attributes = attributes;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            return bos.toByteArray();
        } finally {
            bos.close();
        }
    }
}
