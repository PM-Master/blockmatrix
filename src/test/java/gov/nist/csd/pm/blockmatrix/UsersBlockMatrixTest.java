package gov.nist.csd.pm.blockmatrix;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UsersBlockMatrixTest {

    @Test
    void testAddUser() throws BlockMatrixException {
        UsersBlockMatrix bm = new UsersBlockMatrix(1);
        bm.addUser("u1", "ua1", "ua2");

        UserBlock u1 = bm.getUser("u1");
        assertEquals("u1", u1.getUsername());
        assertTrue(u1.getAttributes().containsAll(Arrays.asList("ua1", "ua2")));
    }

    @Test
    void testRemoveUser() throws BlockMatrixException {
        UsersBlockMatrix bm = new UsersBlockMatrix(1);
        bm.addUser("u1", "ua1", "ua2");
        bm.removeUser("u1");
        assertThrows(BlockMatrixException.class, () -> {bm.getUser("u1");});
    }

    @Test
    void testGetUser() throws BlockMatrixException {
        UsersBlockMatrix bm = new UsersBlockMatrix(1);
        bm.addUser("u1", "ua1", "ua2");

        UserBlock u1 = bm.getUser("u1");
        assertEquals("u1", u1.getUsername());
        assertTrue(u1.getAttributes().containsAll(Arrays.asList("ua1", "ua2")));
    }

    @Test
    void testUpdateUser() throws BlockMatrixException {
        UsersBlockMatrix bm = new UsersBlockMatrix(1);
        bm.addUser("u1", "ua1", "ua2");
        bm.updateUser("u1", "ua1", "ua2", "ua3");

        UserBlock u1 = bm.getUser("u1");
        assertEquals("u1", u1.getUsername());
        assertTrue(u1.getAttributes().containsAll(Arrays.asList("ua1", "ua2", "ua3")));
    }
}