package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class Bill extends JFrame {

    public Bill (String meterNo) {

        setTitle("Bill Details");
        setSize(700, 650);
        setLocation(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);

        JTable billTable = new JTable();
        billTable.setRowHeight(25);

        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM bill WHERE meter_no = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, meterNo);

            ResultSet rs = ps.executeQuery();
            billTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(billTable);
        scrollPane.setBounds(0, 0, 700, 650);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BillDetails("");
    }
}
