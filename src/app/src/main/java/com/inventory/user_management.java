package com.inventory;

import android.content.res.TypedArray;

public class user_management {
    private static user_management myInstance = null;

    D_ArrayImp<User> user_list;
    AVLTreeImp<User> user_tree;
    HashMap<Long ,User> user_hashmap;
    int DAUsers, AVLUsers, HashMapUsers;

    public user_management(){
        user_list = new D_ArrayImp<>();
        user_tree = new AVLTreeImp<>();
        user_hashmap = new HashMap<>(10);
        AVLUsers = 0;
    }

    public void addUser(User user) {
        user_list.add(user);
        DAUsers++;
    }

    public void addUserAVL(User user) {
        user_tree.insert(user);
        AVLUsers++;
    }

    public void addUserHashMap(User user) {
        user_hashmap.add(user.id, user);
        HashMapUsers++;
    }

    public int amountOfAVLUsers(){
        return AVLUsers;
    }

    public static synchronized user_management getInstance() {
        if(myInstance==null) {
            myInstance = new user_management();
        }
        return myInstance;
    }

    public User findAVL(User user) {
        return user_tree.findData(user);
    }

/*<<<<<<< HEAD
<<<<<<< HEAD
    public User findDA(User user){
        return user_list;
    }
=======
>>>>>>> 50f14424b373844bc1fd68a5878f09cfd87fc1e5
=======*/
    public User findHashMap(User user) {
        return user_hashmap.get(user.id);
    }
//>>>>>>> eb4441ae9e58d1865070091ef87fb68ffd35eace


    public String greetAVLUser(User user) {
        return"¡Bienvenido "+findAVL(user).name+"!";
    }

    public D_ArrayImp<User> getInOrderAVL() {
        return user_tree.getInOrder();
    }

    public D_ArrayImp<User> filterAVL(long idStart) {
        User userStart = new User(idStart, "", "", false);
        if(idStart%10==9) {
            return user_tree.rangeSearchB(userStart);
        }
        User userEnd = new User(idStart+1, "", "", false);
        return user_tree.rangeSearchN(userStart, userEnd);
    }

    public void deleteAVL(User userToDel) {
        user_tree.delete(user_tree.find(userToDel));
    }
}
