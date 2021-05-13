package optional;

import optional.dao.ServerDAO;

public class Main {
    public static void main(String[] args) {
        ServerDAO.createSocket(4444);
    }
}
