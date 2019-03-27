package pl.com.sda.dao;

import pl.com.sda.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientFileDAO implements ClientDAO {

    private static final String CLIENT = "CLIENT";
    private static final String CURRENT = "CURRENT";
    private static final String SAVING = "SAVING";
    private static final String CORPORATE = "CORPORATE";
    private static final String COMMA = ",";
    private static final String FILENAME = "clients.txt";

    private BufferedWriter bw;

    private BufferedReader br;

    public ClientFileDAO() {
        File file = new File(FILENAME);
        try {
//            bw = new BufferedWriter(new FileWriter(file));
            br = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(List<Client> clients) {
        File file = new File(FILENAME);
        try {
            bw = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        for (Client c : clients) {
            String row = createClientRow(sb, c);
            saveRowToFile(row);

            sb.setLength(0); //czyszczenie stringbuildera

            for(AbstractAccount ac : c.getAccounts()) {
                row = createAccountRow(sb, ac);
                saveRowToFile(row);
                sb.setLength(0); //czyszczenie stringbuildera
            }
        }

        try {
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String createAccountRow(StringBuilder sb, AbstractAccount ac) {
        return sb.append(ac.getAccountType().name())
                .append(COMMA)
                .append(ac.getNumber())
                .append(COMMA)
                .append(ac.getBalance())
                .toString();
    }



    private String createClientRow(StringBuilder sb, Client client) {
        return sb.append(CLIENT)
                .append(COMMA)
                .append(client.getName())
                .append(COMMA)
                .append(client.getSurname())
                .append(COMMA)
                .append(client.getLogin())
                .append(COMMA)
                .append(client.getPassword())
                .append(COMMA)
                .append(client.getAddress())
                .toString();
    }


    private void saveRowToFile(String row) {
        try {
            bw.write(row);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Client> get() {

        List<Client> clients = new ArrayList<>();

        try {
            String row;


            Client client = null;

            while ((row = br.readLine()) != null) {

                String[] tab = row.split(",");

                if (tab[0].equals(CLIENT)) {
                    client = new Client(tab[1], tab[2], tab[3],
                            tab[4], tab[5], null);

                    clients.add(client);
                } else {
                    AccountType type = AccountType.valueOf(tab[0]);

                    AbstractAccount ac;

                    switch (type) {

                        case CURRENT: {
                            ac = new CurrentAccount(tab[1],
                                    Double.parseDouble(tab[2]));
                            client.getAccounts().add(ac);
                            break;
                        }

                        case SAVING: {
                            ac = new SavingAccount(tab[1],
                                    Double.parseDouble(tab[2]));
                            client.getAccounts().add(ac);
                            break;
                        }
                        case CORPORATE:
                            break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public void close() {
        try {
            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
