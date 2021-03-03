# blockmatrix
An API to manage users and attributes using a blockmatrix.

*Note: This is only a library that uses a block matrix data structure. It is not a distriubted deployment of a block matrix.*

## Maven
First import the jitpack repository.
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io/</url>
    </repository>
</repositories>
```
Then import the blockmatrix dependency.
```xml
<dependency>
    <groupId>com.github.PM-Master</groupId>
    <artifactId>blockmatrix</artifactId>
    <version>LATEST</version>
</dependency>
```

## Usage
### New UsersBlockMatrix
```java
UsersBlockMatrix bm = new UsersBlockMatrix(NUM_USERS);
```
Creates a new `UsersBlockMatrix` with the size `NUM_USERS`. This is the number of users that can fit in the block matrix.  **This cannot be changed later.**
### Add User
```java
bm.addUser("u1", "ua1", "ua2");
```
Adds a new user to the blockmatrix with the user attributes `ua1` and `ua2`
### Get User
```java
UserBlock user = b.getUser("u1");
```
A `UserBlock` has two fields:
```java
private String username;
private Collection<String> attributes;
```
### Update User
```java
bm.updateUser("u1", "ua1", "ua2", "ua3");
```
Updates the attributes for `u1` to `ua1`, `ua2`, and `ua3`.
### Remove User
```java
bm.remove("u1");
```
Removes the user from the blockmatrix.
