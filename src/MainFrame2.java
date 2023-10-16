import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainFrame2 extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JPanel panelEast_1;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame2 frame = new MainFrame2();
                    frame.setVisible(true);
                    frame.setResizable(false); // Make the frame non-resizable
                    frame.pack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame2() {

        setTitle("Companion Harmony Visualizer Tool by AL2D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1157, 864);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);

        JMenuItem menuItemExport = new JMenuItem("Export as txt...");
        menuFile.add(menuItemExport);

        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuFile.add(menuItemExit);

        JMenu menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);

        JMenuItem mntmNewMenuItem = new JMenuItem("About...");
        menuHelp.add(mntmNewMenuItem);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelSouth = new JPanel();
        contentPane.add(panelSouth, BorderLayout.SOUTH);

        JButton buttonReset = new JButton("Clear Selections");

        panelSouth.add(buttonReset);

        JButton buttonToolInfo = new JButton("Tool Information...");

        panelSouth.add(buttonToolInfo);

        JButton buttonCompanionInfo = new JButton("Companion Information...");
        panelSouth.add(buttonCompanionInfo);

        JPanel panelWest = new JPanel();

        JScrollPane scrollPaneWest = new JScrollPane(panelWest);
        scrollPaneWest.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPaneWest, BorderLayout.WEST);

        JPanel panelCenter = new JPanel();
        contentPane.add(panelCenter, BorderLayout.CENTER);

        JScrollPane scrollPaneTable = new JScrollPane();
        panelCenter.add(scrollPaneTable);

        VanillaTableModel vanillaTableModel = new VanillaTableModel();
        JTable table = new JTable(vanillaTableModel);
        scrollPaneTable.setViewportView(table);

        table.setRowHeight(30);

        Dimension tableSize = new Dimension(1000, 530);
        scrollPaneTable.setPreferredSize(tableSize);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(3); //
        columnModel.getColumn(1).setPreferredWidth(50); //
        columnModel.getColumn(2).setPreferredWidth(50); //
        columnModel.getColumn(3).setPreferredWidth(50); //
        columnModel.getColumn(4).setPreferredWidth(50); //
        columnModel.getColumn(5).setPreferredWidth(200); //
        columnModel.getColumn(6).setPreferredWidth(10); //
        columnModel.getColumn(7).setPreferredWidth(250); //
        columnModel.getColumn(8).setPreferredWidth(2); //

        // Create an instance of the composite renderer
        CompositeCellRenderer compositeRenderer = new CompositeCellRenderer();

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(compositeRenderer);
        }

        // Hide Color column
        TableColumnModel columnModelHide = table.getColumnModel();
        TableColumn column = columnModelHide.getColumn(9);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setResizable(false);

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("TEXT_CHANGE_LATER");
        panelNorth.add(lblNewLabel);

        JPanel panelEast = new JPanel();
        contentPane.add(panelEast, BorderLayout.EAST);

        panelWest = new JPanel();
        contentPane.add(panelWest, BorderLayout.WEST);
        GridBagLayout gbl_panelWest = new GridBagLayout();
        gbl_panelWest.columnWidths = new int[]{0, 0};
        gbl_panelWest.rowHeights = new int[] {30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panelWest.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panelWest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelWest.setLayout(gbl_panelWest);

        JLabel labelCompanions = new JLabel("Choose the Companions   ");
        GridBagConstraints gbc_labelCompanions = new GridBagConstraints();
        gbc_labelCompanions.insets = new Insets(0, 0, 5, 0);
        gbc_labelCompanions.gridx = 0;
        gbc_labelCompanions.gridy = 0;
        panelWest.add(labelCompanions, gbc_labelCompanions);
        labelCompanions.setForeground(Color.BLUE);

        JCheckBox deshaviCheckBox = new JCheckBox("Deshavi");
        GridBagConstraints gbc_deshaviCheckBox = new GridBagConstraints();
        gbc_deshaviCheckBox.anchor = GridBagConstraints.WEST;
        gbc_deshaviCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_deshaviCheckBox.gridx = 0;
        gbc_deshaviCheckBox.gridy = 1;
        panelWest.add(deshaviCheckBox, gbc_deshaviCheckBox);

        JCheckBox firentisCheckBox = new JCheckBox("Firentis");
        GridBagConstraints gbc_firentisCheckBox = new GridBagConstraints();
        gbc_firentisCheckBox.anchor = GridBagConstraints.WEST;
        gbc_firentisCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_firentisCheckBox.gridx = 0;
        gbc_firentisCheckBox.gridy = 2;
        panelWest.add(firentisCheckBox, gbc_firentisCheckBox);

        JCheckBox jeremusCheckBox = new JCheckBox("Jeremus");
        GridBagConstraints gbc_jeremusCheckBox = new GridBagConstraints();
        gbc_jeremusCheckBox.anchor = GridBagConstraints.WEST;
        gbc_jeremusCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_jeremusCheckBox.gridx = 0;
        gbc_jeremusCheckBox.gridy = 3;
        panelWest.add(jeremusCheckBox, gbc_jeremusCheckBox);

        JCheckBox marnidCheckBox = new JCheckBox("Marnid");
        GridBagConstraints gbc_marnidCheckBox = new GridBagConstraints();
        gbc_marnidCheckBox.anchor = GridBagConstraints.WEST;
        gbc_marnidCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_marnidCheckBox.gridx = 0;
        gbc_marnidCheckBox.gridy = 4;
        panelWest.add(marnidCheckBox, gbc_marnidCheckBox);

        JCheckBox ymiraCheckBox = new JCheckBox("Ymira");
        GridBagConstraints gbc_ymiraCheckBox = new GridBagConstraints();
        gbc_ymiraCheckBox.anchor = GridBagConstraints.WEST;
        gbc_ymiraCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_ymiraCheckBox.gridx = 0;
        gbc_ymiraCheckBox.gridy = 5;
        panelWest.add(ymiraCheckBox, gbc_ymiraCheckBox);

        JCheckBox katrinCheckBox = new JCheckBox("Katrin");
        GridBagConstraints gbc_katrinCheckBox = new GridBagConstraints();
        gbc_katrinCheckBox.anchor = GridBagConstraints.WEST;
        gbc_katrinCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_katrinCheckBox.gridx = 0;
        gbc_katrinCheckBox.gridy = 6;
        panelWest.add(katrinCheckBox, gbc_katrinCheckBox);

        JCheckBox bundukCheckBox = new JCheckBox("Bunduk");
        GridBagConstraints gbc_bundukCheckBox = new GridBagConstraints();
        gbc_bundukCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_bundukCheckBox.anchor = GridBagConstraints.WEST;
        gbc_bundukCheckBox.gridx = 0;
        gbc_bundukCheckBox.gridy = 7;
        panelWest.add(bundukCheckBox, gbc_bundukCheckBox);

        JCheckBox klethiCheckBox = new JCheckBox("Klethi");
        GridBagConstraints gbc_klethiCheckBox = new GridBagConstraints();
        gbc_klethiCheckBox.anchor = GridBagConstraints.WEST;
        gbc_klethiCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_klethiCheckBox.gridx = 0;
        gbc_klethiCheckBox.gridy = 8;
        panelWest.add(klethiCheckBox, gbc_klethiCheckBox);

        JCheckBox alayenCheckBox = new JCheckBox("Alayen");
        GridBagConstraints gbc_alayenCheckBox = new GridBagConstraints();
        gbc_alayenCheckBox.anchor = GridBagConstraints.WEST;
        gbc_alayenCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_alayenCheckBox.gridx = 0;
        gbc_alayenCheckBox.gridy = 9;
        panelWest.add(alayenCheckBox, gbc_alayenCheckBox);

        JCheckBox artimennerCheckBox = new JCheckBox("Artimenner");
        GridBagConstraints gbc_artimennerCheckBox = new GridBagConstraints();
        gbc_artimennerCheckBox.anchor = GridBagConstraints.WEST;
        gbc_artimennerCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_artimennerCheckBox.gridx = 0;
        gbc_artimennerCheckBox.gridy = 10;
        panelWest.add(artimennerCheckBox, gbc_artimennerCheckBox);

        JCheckBox borchaCheckBox = new JCheckBox("Borcha");
        GridBagConstraints gbc_borchaCheckBox = new GridBagConstraints();
        gbc_borchaCheckBox.anchor = GridBagConstraints.WEST;
        gbc_borchaCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_borchaCheckBox.gridx = 0;
        gbc_borchaCheckBox.gridy = 11;
        panelWest.add(borchaCheckBox, gbc_borchaCheckBox);

        JCheckBox nizarCheckBox = new JCheckBox("Nizar");
        GridBagConstraints gbc_nizarCheckBox = new GridBagConstraints();
        gbc_nizarCheckBox.anchor = GridBagConstraints.WEST;
        gbc_nizarCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_nizarCheckBox.gridx = 0;
        gbc_nizarCheckBox.gridy = 12;
        panelWest.add(nizarCheckBox, gbc_nizarCheckBox);

        JCheckBox rolfCheckBox = new JCheckBox("Rolf");
        GridBagConstraints gbc_rolfCheckBox = new GridBagConstraints();
        gbc_rolfCheckBox.anchor = GridBagConstraints.WEST;
        gbc_rolfCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_rolfCheckBox.gridx = 0;
        gbc_rolfCheckBox.gridy = 13;
        panelWest.add(rolfCheckBox, gbc_rolfCheckBox);

        JCheckBox baheshturCheckBox = new JCheckBox("Baheshtur");
        GridBagConstraints gbc_baheshturCheckBox = new GridBagConstraints();
        gbc_baheshturCheckBox.anchor = GridBagConstraints.NORTHWEST;
        gbc_baheshturCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_baheshturCheckBox.gridx = 0;
        gbc_baheshturCheckBox.gridy = 14;
        panelWest.add(baheshturCheckBox, gbc_baheshturCheckBox);

        JCheckBox lezalitCheckBox = new JCheckBox("Lezalit");
        GridBagConstraints gbc_lezalitCheckBox = new GridBagConstraints();
        gbc_lezalitCheckBox.anchor = GridBagConstraints.WEST;
        gbc_lezalitCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_lezalitCheckBox.gridx = 0;
        gbc_lezalitCheckBox.gridy = 15;
        panelWest.add(lezalitCheckBox, gbc_lezalitCheckBox);

        JCheckBox matheldCheckBox = new JCheckBox("Matheld");
        GridBagConstraints gbc_matheldCheckBox = new GridBagConstraints();
        gbc_matheldCheckBox.anchor = GridBagConstraints.WEST;
        gbc_matheldCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_matheldCheckBox.gridx = 0;
        gbc_matheldCheckBox.gridy = 16;
        panelWest.add(matheldCheckBox, gbc_matheldCheckBox);

// Add action listeners for the checkboxes
        // Create a common action listener template
        ActionListener checkBoxActionListener = e -> {
            JCheckBox checkBox = (JCheckBox) e.getSource(); // Get the source of the event
            String companionName = checkBox.getText();
            CompanionVanilla companion = vanillaTableModel.getCompanionByName(companionName);

            if (checkBox.isSelected()) {
                companion.setvIsChoosen(true);
            } else {
                companion.setvIsChoosen(false);
            }
            vanillaTableModel.setRowBackgroundColor();
            // Call fireTableDataChanged once after all changes are made
            SwingUtilities.invokeLater(() -> vanillaTableModel.fireTableDataChanged());
        };
// Assign the common action listener to all checkboxes
        deshaviCheckBox.addActionListener(checkBoxActionListener);
        firentisCheckBox.addActionListener(checkBoxActionListener);
        jeremusCheckBox.addActionListener(checkBoxActionListener);
        marnidCheckBox.addActionListener(checkBoxActionListener);
        ymiraCheckBox.addActionListener(checkBoxActionListener);
        katrinCheckBox.addActionListener(checkBoxActionListener);
        bundukCheckBox.addActionListener(checkBoxActionListener);
        klethiCheckBox.addActionListener(checkBoxActionListener);
        alayenCheckBox.addActionListener(checkBoxActionListener);
        artimennerCheckBox.addActionListener(checkBoxActionListener);
        borchaCheckBox.addActionListener(checkBoxActionListener);
        nizarCheckBox.addActionListener(checkBoxActionListener);
        rolfCheckBox.addActionListener(checkBoxActionListener);
        baheshturCheckBox.addActionListener(checkBoxActionListener);
        lezalitCheckBox.addActionListener(checkBoxActionListener);
        matheldCheckBox.addActionListener(checkBoxActionListener);





        panelEast_1 = new JPanel();
        contentPane.add(panelEast_1, BorderLayout.EAST);
        GridBagLayout gbl_panelEast = new GridBagLayout();
        gbl_panelEast.columnWidths = new int[] {0, 20};
        gbl_panelEast.rowHeights = new int[] {30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30};
        gbl_panelEast.columnWeights = new double[]{0.0, 0.0};
        gbl_panelEast.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelEast_1.setLayout(gbl_panelEast);

        JLabel lblpsTitelSkills = new JLabel("Skills potentially covered:");
        GridBagConstraints gbc_lblpsTitelSkills = new GridBagConstraints();
        gbc_lblpsTitelSkills.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsTitelSkills.gridx = 0;
        gbc_lblpsTitelSkills.gridy = 0;
        panelEast_1.add(lblpsTitelSkills, gbc_lblpsTitelSkills);
        lblpsTitelSkills.setForeground(Color.BLUE);

        JLabel lblpsTimes = new JLabel("Times:");
        lblpsTimes.setForeground(Color.BLUE);
        GridBagConstraints gbc_lblpsTimes = new GridBagConstraints();
        gbc_lblpsTimes.insets = new Insets(0, 0, 5, 0);
        gbc_lblpsTimes.gridx = 1;
        gbc_lblpsTimes.gridy = 0;
        panelEast_1.add(lblpsTimes, gbc_lblpsTimes);

        JCheckBox chckbxOnlyPartyskillsRelevant = new JCheckBox("Limit to Party Relevant Skills");
        GridBagConstraints gbc_chckbxOnlyPartyskillsRelevant = new GridBagConstraints();
        gbc_chckbxOnlyPartyskillsRelevant.gridwidth = 2;
        gbc_chckbxOnlyPartyskillsRelevant.insets = new Insets(0, 0, 5, 0);
        gbc_chckbxOnlyPartyskillsRelevant.gridx = 0;
        gbc_chckbxOnlyPartyskillsRelevant.gridy = 1;
        panelEast_1.add(chckbxOnlyPartyskillsRelevant, gbc_chckbxOnlyPartyskillsRelevant);

        JLabel lblpsIronflesh = new JLabel("(Pe/S) Ironflesh");
        GridBagConstraints gbc_lblpsIronflesh = new GridBagConstraints();
        gbc_lblpsIronflesh.anchor = GridBagConstraints.WEST;
        gbc_lblpsIronflesh.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsIronflesh.gridx = 0;
        gbc_lblpsIronflesh.gridy = 2;
        lblpsIronflesh.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsIronflesh, gbc_lblpsIronflesh);

        JLabel label_timesIronflesh = new JLabel("0");
        GridBagConstraints gbc_label_timesIronflesh = new GridBagConstraints();
        gbc_label_timesIronflesh.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesIronflesh.gridx = 1;
        gbc_label_timesIronflesh.gridy = 2;
        panelEast_1.add(label_timesIronflesh, gbc_label_timesIronflesh);

        JLabel lblpsPowerStrike = new JLabel("(Pe/S) Power Strike");
        GridBagConstraints gbc_lblpsPowerStrike = new GridBagConstraints();
        gbc_lblpsPowerStrike.anchor = GridBagConstraints.WEST;
        gbc_lblpsPowerStrike.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsPowerStrike.gridx = 0;
        gbc_lblpsPowerStrike.gridy = 3;
        lblpsPowerStrike.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsPowerStrike, gbc_lblpsPowerStrike);

        JLabel label_timesPowerStrike = new JLabel("0");
        GridBagConstraints gbc_label_timesPowerStrike = new GridBagConstraints();
        gbc_label_timesPowerStrike.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesPowerStrike.gridx = 1;
        gbc_label_timesPowerStrike.gridy = 3;
        panelEast_1.add(label_timesPowerStrike, gbc_label_timesPowerStrike);

        JLabel lblpsPowerThrow = new JLabel("(Pe/S) Power Throw");
        GridBagConstraints gbc_lblpsPowerThrow = new GridBagConstraints();
        gbc_lblpsPowerThrow.anchor = GridBagConstraints.WEST;
        gbc_lblpsPowerThrow.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsPowerThrow.gridx = 0;
        gbc_lblpsPowerThrow.gridy = 4;
        lblpsPowerThrow.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsPowerThrow, gbc_lblpsPowerThrow);

        JLabel label_timesPowerThrow = new JLabel("0");
        GridBagConstraints gbc_label_timesPowerThrow = new GridBagConstraints();
        gbc_label_timesPowerThrow.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesPowerThrow.gridx = 1;
        gbc_label_timesPowerThrow.gridy = 4;
        panelEast_1.add(label_timesPowerThrow, gbc_label_timesPowerThrow);

        JLabel lblpsPowerDraw = new JLabel("(Pe/S) Power Draw");
        GridBagConstraints gbc_lblpsPowerDraw = new GridBagConstraints();
        gbc_lblpsPowerDraw.anchor = GridBagConstraints.WEST;
        gbc_lblpsPowerDraw.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsPowerDraw.gridx = 0;
        gbc_lblpsPowerDraw.gridy = 5;
        lblpsPowerDraw.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsPowerDraw, gbc_lblpsPowerDraw);

        JLabel label_timesPowerDraw = new JLabel("0");
        GridBagConstraints gbc_label_timesPowerDraw = new GridBagConstraints();
        gbc_label_timesPowerDraw.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesPowerDraw.gridx = 1;
        gbc_label_timesPowerDraw.gridy = 5;
        panelEast_1.add(label_timesPowerDraw, gbc_label_timesPowerDraw);

        JLabel lblpsWeaponMaster = new JLabel("(Pe/A) Weapon Master");
        GridBagConstraints gbc_lblpsWeaponMaster = new GridBagConstraints();
        gbc_lblpsWeaponMaster.anchor = GridBagConstraints.WEST;
        gbc_lblpsWeaponMaster.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsWeaponMaster.gridx = 0;
        gbc_lblpsWeaponMaster.gridy = 6;
        lblpsWeaponMaster.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsWeaponMaster, gbc_lblpsWeaponMaster);

        JLabel label_timesWeaponMaster = new JLabel("0");
        GridBagConstraints gbc_label_timesWeaponMaster = new GridBagConstraints();
        gbc_label_timesWeaponMaster.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesWeaponMaster.gridx = 1;
        gbc_label_timesWeaponMaster.gridy = 6;
        panelEast_1.add(label_timesWeaponMaster, gbc_label_timesWeaponMaster);

// Agility Skills
        JLabel lblpsShield = new JLabel("(Pe/A) Shield");
        GridBagConstraints gbc_lblpsShield = new GridBagConstraints();
        gbc_lblpsShield.anchor = GridBagConstraints.WEST;
        gbc_lblpsShield.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsShield.gridx = 0;
        gbc_lblpsShield.gridy = 7;
        lblpsShield.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsShield, gbc_lblpsShield);

        JLabel label_timesShield = new JLabel("0");
        GridBagConstraints gbc_label_timesShield = new GridBagConstraints();
        gbc_label_timesShield.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesShield.gridx = 1;
        gbc_label_timesShield.gridy = 7;
        panelEast_1.add(label_timesShield, gbc_label_timesShield);

        JLabel lblpsAthletics = new JLabel("(Pe/A) Athletics");
        GridBagConstraints gbc_lblpsAthletics = new GridBagConstraints();
        gbc_lblpsAthletics.anchor = GridBagConstraints.WEST;
        gbc_lblpsAthletics.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsAthletics.gridx = 0;
        gbc_lblpsAthletics.gridy = 8;
        lblpsAthletics.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsAthletics, gbc_lblpsAthletics);

        JLabel label_timesAthletics = new JLabel("0");
        GridBagConstraints gbc_label_timesAthletics = new GridBagConstraints();
        gbc_label_timesAthletics.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesAthletics.gridx = 1;
        gbc_label_timesAthletics.gridy = 8;
        panelEast_1.add(label_timesAthletics, gbc_label_timesAthletics);

        JLabel lblpsRiding = new JLabel("(Pe/A) Riding");
        GridBagConstraints gbc_lblpsRiding = new GridBagConstraints();
        gbc_lblpsRiding.anchor = GridBagConstraints.WEST;
        gbc_lblpsRiding.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsRiding.gridx = 0;
        gbc_lblpsRiding.gridy = 9;
        lblpsRiding.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsRiding, gbc_lblpsRiding);

        JLabel label_timesRiding = new JLabel("0");
        GridBagConstraints gbc_label_timesRiding = new GridBagConstraints();
        gbc_label_timesRiding.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesRiding.gridx = 1;
        gbc_label_timesRiding.gridy = 9;
        panelEast_1.add(label_timesRiding, gbc_label_timesRiding);

        JLabel lblpsHorseArchery = new JLabel("(Pe/A) Horse Archery");
        GridBagConstraints gbc_lblpsHorseArchery = new GridBagConstraints();
        gbc_lblpsHorseArchery.anchor = GridBagConstraints.WEST;
        gbc_lblpsHorseArchery.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsHorseArchery.gridx = 0;
        gbc_lblpsHorseArchery.gridy = 10;
        lblpsHorseArchery.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsHorseArchery, gbc_lblpsHorseArchery);

        JLabel label_timesHorseArchery = new JLabel("0");
        GridBagConstraints gbc_label_timesHorseArchery = new GridBagConstraints();
        gbc_label_timesHorseArchery.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesHorseArchery.gridx = 1;
        gbc_label_timesHorseArchery.gridy = 10;
        panelEast_1.add(label_timesHorseArchery, gbc_label_timesHorseArchery);

        JLabel lblpsLooting = new JLabel("(PA/I) Looting");
        GridBagConstraints gbc_lblpsLooting = new GridBagConstraints();
        gbc_lblpsLooting.anchor = GridBagConstraints.WEST;
        gbc_lblpsLooting.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsLooting.gridx = 0;
        gbc_lblpsLooting.gridy = 11;
        lblpsLooting.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsLooting, gbc_lblpsLooting);

        JLabel label_timesLooting = new JLabel("0");
        GridBagConstraints gbc_label_timesLooting = new GridBagConstraints();
        gbc_label_timesLooting.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesLooting.gridx = 1;
        gbc_label_timesLooting.gridy = 11;
        panelEast_1.add(label_timesLooting, gbc_label_timesLooting);

// Intelligence Skills (Personal)
        JLabel lblpsTrainer = new JLabel("(Pe/I) Trainer");
        GridBagConstraints gbc_lblpsTrainer = new GridBagConstraints();
        gbc_lblpsTrainer.anchor = GridBagConstraints.WEST;
        gbc_lblpsTrainer.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsTrainer.gridx = 0;
        gbc_lblpsTrainer.gridy = 12;
        lblpsTrainer.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsTrainer, gbc_lblpsTrainer);

        JLabel label_timesTrainer = new JLabel("0");
        GridBagConstraints gbc_label_timesTrainer = new GridBagConstraints();
        gbc_label_timesTrainer.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesTrainer.gridx = 1;
        gbc_label_timesTrainer.gridy = 12;
        panelEast_1.add(label_timesTrainer, gbc_label_timesTrainer);

        JLabel lblpsTracking = new JLabel("(PA/I) Tracking");
        GridBagConstraints gbc_lblpsTracking = new GridBagConstraints();
        gbc_lblpsTracking.anchor = GridBagConstraints.WEST;
        gbc_lblpsTracking.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsTracking.gridx = 0;
        gbc_lblpsTracking.gridy = 13;
        lblpsTracking.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsTracking, gbc_lblpsTracking);

        JLabel label_timesTracking = new JLabel("0");
        GridBagConstraints gbc_label_timesTracking = new GridBagConstraints();
        gbc_label_timesTracking.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesTracking.gridx = 1;
        gbc_label_timesTracking.gridy = 13;
        panelEast_1.add(label_timesTracking, gbc_label_timesTracking);

        JLabel lblpsTactics = new JLabel("(PA/I) Tactics");
        GridBagConstraints gbc_lblpsTactics = new GridBagConstraints();
        gbc_lblpsTactics.anchor = GridBagConstraints.WEST;
        gbc_lblpsTactics.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsTactics.gridx = 0;
        gbc_lblpsTactics.gridy = 14;
        lblpsTactics.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsTactics, gbc_lblpsTactics);

        JLabel label_timesTactics = new JLabel("0");
        GridBagConstraints gbc_label_timesTactics = new GridBagConstraints();
        gbc_label_timesTactics.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesTactics.gridx = 1;
        gbc_label_timesTactics.gridy = 14;
        panelEast_1.add(label_timesTactics, gbc_label_timesTactics);

        JLabel lblpsPathFinding = new JLabel("(PA/I) Path-finding");
        GridBagConstraints gbc_lblpsPathFinding = new GridBagConstraints();
        gbc_lblpsPathFinding.anchor = GridBagConstraints.WEST;
        gbc_lblpsPathFinding.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsPathFinding.gridx = 0;
        gbc_lblpsPathFinding.gridy = 15;
        lblpsPathFinding.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsPathFinding, gbc_lblpsPathFinding);

        JLabel label_timesPathFinding = new JLabel("0");
        GridBagConstraints gbc_label_timesPathFinding = new GridBagConstraints();
        gbc_label_timesPathFinding.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesPathFinding.gridx = 1;
        gbc_label_timesPathFinding.gridy = 15;
        panelEast_1.add(label_timesPathFinding, gbc_label_timesPathFinding);

        JLabel lblpsSpotting2 = new JLabel("(PA/I) Spotting");
        GridBagConstraints gbc_lblpsSpotting2 = new GridBagConstraints();
        gbc_lblpsSpotting2.anchor = GridBagConstraints.WEST;
        gbc_lblpsSpotting2.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsSpotting2.gridx = 0;
        gbc_lblpsSpotting2.gridy = 16;
        lblpsSpotting2.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsSpotting2, gbc_lblpsSpotting2);

        JLabel label_timesSpotting = new JLabel("0");
        GridBagConstraints gbc_label_timesSpotting = new GridBagConstraints();
        gbc_label_timesSpotting.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesSpotting.gridx = 1;
        gbc_label_timesSpotting.gridy = 16;
        panelEast_1.add(label_timesSpotting, gbc_label_timesSpotting);

        JLabel lblpsInventoryManagement = new JLabel("(Le/I) Inventory Management");
        GridBagConstraints gbc_lblpsInventoryManagement = new GridBagConstraints();
        gbc_lblpsInventoryManagement.anchor = GridBagConstraints.WEST;
        gbc_lblpsInventoryManagement.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsInventoryManagement.gridx = 0;
        gbc_lblpsInventoryManagement.gridy = 17;
        lblpsInventoryManagement.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsInventoryManagement, gbc_lblpsInventoryManagement);

        JLabel label_timesInventoryManagement = new JLabel("0");
        GridBagConstraints gbc_label_timesInventoryManagement = new GridBagConstraints();
        gbc_label_timesInventoryManagement.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesInventoryManagement.gridx = 1;
        gbc_label_timesInventoryManagement.gridy = 17;
        panelEast_1.add(label_timesInventoryManagement, gbc_label_timesInventoryManagement);

        JLabel lblpsWoundTreatment = new JLabel("(PA/I) Wound Treatment");
        GridBagConstraints gbc_lblpsWoundTreatment = new GridBagConstraints();
        gbc_lblpsWoundTreatment.anchor = GridBagConstraints.WEST;
        gbc_lblpsWoundTreatment.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsWoundTreatment.gridx = 0;
        gbc_lblpsWoundTreatment.gridy = 18;
        lblpsWoundTreatment.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsWoundTreatment, gbc_lblpsWoundTreatment);

        JLabel label_timesWoundTreatment = new JLabel("0");
        GridBagConstraints gbc_label_timesWoundTreatment = new GridBagConstraints();
        gbc_label_timesWoundTreatment.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesWoundTreatment.gridx = 1;
        gbc_label_timesWoundTreatment.gridy = 18;
        panelEast_1.add(label_timesWoundTreatment, gbc_label_timesWoundTreatment);

        JLabel lblpsSurgery = new JLabel("(PA/I) Surgery");
        GridBagConstraints gbc_lblpsSurgery = new GridBagConstraints();
        gbc_lblpsSurgery.anchor = GridBagConstraints.WEST;
        gbc_lblpsSurgery.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsSurgery.gridx = 0;
        gbc_lblpsSurgery.gridy = 19;
        lblpsSurgery.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsSurgery, gbc_lblpsSurgery);

        JLabel label_timesWoundSurgery = new JLabel("0");
        GridBagConstraints gbc_label_timesWoundSurgery = new GridBagConstraints();
        gbc_label_timesWoundSurgery.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesWoundSurgery.gridx = 1;
        gbc_label_timesWoundSurgery.gridy = 19;
        panelEast_1.add(label_timesWoundSurgery, gbc_label_timesWoundSurgery);

        JLabel lblpsFirstAid = new JLabel("(PA/I) First Aid");
        GridBagConstraints gbc_lblpsFirstAid = new GridBagConstraints();
        gbc_lblpsFirstAid.anchor = GridBagConstraints.WEST;
        gbc_lblpsFirstAid.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsFirstAid.gridx = 0;
        gbc_lblpsFirstAid.gridy = 20;
        lblpsFirstAid.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsFirstAid, gbc_lblpsFirstAid);

        JLabel label_timesFirstAid = new JLabel("0");
        GridBagConstraints gbc_label_timesFirstAid = new GridBagConstraints();
        gbc_label_timesFirstAid.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesFirstAid.gridx = 1;
        gbc_label_timesFirstAid.gridy = 20;
        panelEast_1.add(label_timesFirstAid, gbc_label_timesFirstAid);

        JLabel lblpsEngineer = new JLabel("(PA/I) Engineer");
        GridBagConstraints gbc_lblpsEngineer = new GridBagConstraints();
        gbc_lblpsEngineer.anchor = GridBagConstraints.WEST;
        gbc_lblpsEngineer.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsEngineer.gridx = 0;
        gbc_lblpsEngineer.gridy = 21;
        lblpsEngineer.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsEngineer, gbc_lblpsEngineer);

        JLabel label_timesEngineer = new JLabel("0");
        GridBagConstraints gbc_label_timesEngineer = new GridBagConstraints();
        gbc_label_timesEngineer.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesEngineer.gridx = 1;
        gbc_label_timesEngineer.gridy = 21;
        panelEast_1.add(label_timesEngineer, gbc_label_timesEngineer);

        JLabel lblpsPersuasion = new JLabel("(Pe/I) Persuasion");
        lblpsPersuasion.setToolTipText("");
        GridBagConstraints gbc_lblpsPersuasion = new GridBagConstraints();
        gbc_lblpsPersuasion.anchor = GridBagConstraints.WEST;
        gbc_lblpsPersuasion.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsPersuasion.gridx = 0;
        gbc_lblpsPersuasion.gridy = 22;
        lblpsPersuasion.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsPersuasion, gbc_lblpsPersuasion);

        JLabel label_timesPersuasion = new JLabel("0");
        GridBagConstraints gbc_label_timesPersuasion = new GridBagConstraints();
        gbc_label_timesPersuasion.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesPersuasion.gridx = 1;
        gbc_label_timesPersuasion.gridy = 22;
        panelEast_1.add(label_timesPersuasion, gbc_label_timesPersuasion);

        JLabel lblpsPrisonerManagement = new JLabel("(Le/C) Prisoner Management");
        GridBagConstraints gbc_lblpsPrisonerManagement = new GridBagConstraints();
        gbc_lblpsPrisonerManagement.anchor = GridBagConstraints.WEST;
        gbc_lblpsPrisonerManagement.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsPrisonerManagement.gridx = 0;
        gbc_lblpsPrisonerManagement.gridy = 23;
        lblpsPrisonerManagement.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsPrisonerManagement, gbc_lblpsPrisonerManagement);

        JLabel label_timesPrisionerManagement = new JLabel("0");
        GridBagConstraints gbc_label_timesPrisionerManagement = new GridBagConstraints();
        gbc_label_timesPrisionerManagement.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesPrisionerManagement.gridx = 1;
        gbc_label_timesPrisionerManagement.gridy = 23;
        panelEast_1.add(label_timesPrisionerManagement, gbc_label_timesPrisionerManagement);

        JLabel lblpsLeadership = new JLabel("(Le/C) Leadership");
        GridBagConstraints gbc_lblpsLeadership = new GridBagConstraints();
        gbc_lblpsLeadership.anchor = GridBagConstraints.WEST;
        gbc_lblpsLeadership.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsLeadership.gridx = 0;
        gbc_lblpsLeadership.gridy = 24;
        lblpsLeadership.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsLeadership, gbc_lblpsLeadership);

        JLabel label_timesLeadership = new JLabel("0");
        GridBagConstraints gbc_label_timesLeadership = new GridBagConstraints();
        gbc_label_timesLeadership.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesLeadership.gridx = 1;
        gbc_label_timesLeadership.gridy = 24;
        panelEast_1.add(label_timesLeadership, gbc_label_timesLeadership);

        JLabel lblpsTrade = new JLabel("(PA/C) Trade");
        GridBagConstraints gbc_lblpsTrade = new GridBagConstraints();
        gbc_lblpsTrade.anchor = GridBagConstraints.WEST;
        gbc_lblpsTrade.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsTrade.gridx = 0;
        gbc_lblpsTrade.gridy = 25;
        lblpsTrade.setForeground(Color.RED); // Set text color to red
        panelEast_1.add(lblpsTrade, gbc_lblpsTrade);

        JLabel label_timesTrader = new JLabel("0");
        GridBagConstraints gbc_label_timesTrader = new GridBagConstraints();
        gbc_label_timesTrader.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesTrader.gridx = 1;
        gbc_label_timesTrader.gridy = 25;
        panelEast_1.add(label_timesTrader, gbc_label_timesTrader);

        chckbxOnlyPartyskillsRelevant.addActionListener(e -> {
            if(chckbxOnlyPartyskillsRelevant.isSelected()){
                lblpsIronflesh.setVisible(false);
                label_timesIronflesh.setVisible(false);
                lblpsPowerStrike.setVisible(false);
                label_timesPowerStrike.setVisible(false);
                lblpsPowerThrow.setVisible(false);
                label_timesPowerThrow.setVisible(false);
                lblpsPowerDraw.setVisible(false);
                label_timesPowerDraw.setVisible(false);
                lblpsWeaponMaster.setVisible(false);
                label_timesWeaponMaster.setVisible(false);
                lblpsShield.setVisible(false);
                label_timesShield.setVisible(false);
                lblpsAthletics.setVisible(false);
                label_timesAthletics.setVisible(false);
                lblpsRiding.setVisible(false);
                label_timesRiding.setVisible(false);
                lblpsHorseArchery.setVisible(false);
                label_timesHorseArchery.setVisible(false);
                lblpsLooting.setVisible(false);
                label_timesLooting.setVisible(false);
                lblpsInventoryManagement.setVisible(false);
                label_timesInventoryManagement.setVisible(false);
                lblpsPrisonerManagement.setVisible(false);
                label_timesPrisionerManagement.setVisible(false);
                lblpsLeadership.setVisible(false);
                label_timesLeadership.setVisible(false);
                lblpsPersuasion.setVisible(false);
                label_timesPersuasion.setVisible(false);
            }else{
                lblpsIronflesh.setVisible(true);
                label_timesIronflesh.setVisible(true);
                lblpsPowerStrike.setVisible(true);
                label_timesPowerStrike.setVisible(true);
                lblpsPowerThrow.setVisible(true);
                label_timesPowerThrow.setVisible(true);
                lblpsPowerDraw.setVisible(true);
                label_timesPowerDraw.setVisible(true);
                lblpsWeaponMaster.setVisible(true);
                label_timesWeaponMaster.setVisible(true);
                lblpsShield.setVisible(true);
                label_timesShield.setVisible(true);
                lblpsAthletics.setVisible(true);
                label_timesAthletics.setVisible(true);
                lblpsRiding.setVisible(true);
                label_timesRiding.setVisible(true);
                lblpsHorseArchery.setVisible(true);
                label_timesHorseArchery.setVisible(true);
                lblpsLooting.setVisible(true);
                label_timesLooting.setVisible(true);
                lblpsInventoryManagement.setVisible(true);
                label_timesInventoryManagement.setVisible(true);
                lblpsPrisonerManagement.setVisible(true);
                label_timesPrisionerManagement.setVisible(true);
                lblpsLeadership.setVisible(true);
                label_timesLeadership.setVisible(true);
                lblpsPersuasion.setVisible(true);
                label_timesPersuasion.setVisible(true);
            }


        });

        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        buttonToolInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * The original code was authored by AL2D and may be subject to modifications by other users.
     * However, please ensure to attribute the tool abbreviation created to AL2D.
     */
        // Dev 0.7.0

    /**
     * The preceding code represents the non-optimized version.
     * Its excessive repetition and size indicate a need for optimization,
     * which I may or may not provide in the future.
     */
}

