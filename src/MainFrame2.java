import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
// MainFrame which basically builds mostly the GUI and does some work too
public class MainFrame2 extends JFrame {

    private JPanel contentPane;
    private JPanel panelEast_1;

    static final Color green = new Color(22, 130, 0);
    static final Color red = new Color(204, 0, 0);
    static final Color gray = new Color(160, 160, 160);

    int numSkills = 24; // sets size for Skills array


    public static int[] skillCounts = new int[24];

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

        // sets up basics for Window creation of the MainFrame

        setTitle("Companion Harmony Visualizer Tool by AL2D   -   v1.0.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1157, 864);

        // Creates the menu on top:

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

        JMenuItem menuItemAbout = new JMenuItem("About...");
        menuHelp.add(menuItemAbout);

        // Layout Stuff:

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelSouth = new JPanel();
        contentPane.add(panelSouth, BorderLayout.SOUTH);

        // Createss button on top:

        JButton buttonReset = new JButton("Clear Selections");
        panelSouth.add(buttonReset);

        JButton buttonToolInfo = new JButton("Tool Information...");
        panelSouth.add(buttonToolInfo);

        JButton buttonCompanionInfo = new JButton("Companion Information...");
        panelSouth.add(buttonCompanionInfo);

        JButton buttonSkillInfo = new JButton("Skill Information...");
        panelSouth.add(buttonSkillInfo);

        // Layout Stuff:

        JPanel panelWest = new JPanel();

        JScrollPane scrollPaneWest = new JScrollPane(panelWest);
        scrollPaneWest.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPaneWest, BorderLayout.WEST);

        JPanel panelCenter = new JPanel();
        contentPane.add(panelCenter, BorderLayout.CENTER);

        JScrollPane scrollPaneTable = new JScrollPane();
        panelCenter.add(scrollPaneTable);

        // Creates the JTable:

        VanillaTableModel vanillaTableModel = new VanillaTableModel();
        JTable table = new JTable(vanillaTableModel);
        scrollPaneTable.setViewportView(table);

        table.setRowHeight(30);

        Dimension tableSize = new Dimension(1000, 530); // sets up a forced tableSize
        scrollPaneTable.setPreferredSize(tableSize);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(3); // the sizes are not 1:1
        columnModel.getColumn(1).setPreferredWidth(50); // because it is forced
        columnModel.getColumn(2).setPreferredWidth(50); // to fill up the tableSize
        columnModel.getColumn(3).setPreferredWidth(50); //
        columnModel.getColumn(4).setPreferredWidth(50); //
        columnModel.getColumn(5).setPreferredWidth(200); //
        columnModel.getColumn(6).setPreferredWidth(10); //
        columnModel.getColumn(7).setPreferredWidth(250); //
        columnModel.getColumn(8).setPreferredWidth(2); //

        // Applies the combined renderer:
        CompositeCellRenderer compositeRenderer = new CompositeCellRenderer();

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(compositeRenderer);
        }

        // Hide Color column which is responsible for displaying, in which color the row should be displayed
        // Theoretically could be put out from the TableModel but for safety reasons is kept in
        TableColumnModel columnModelHide = table.getColumnModel();
        TableColumn column = columnModelHide.getColumn(9);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setResizable(false);

        // Layout Stuff:

        JPanel panelNorth = new JPanel();
        contentPane.add(panelNorth, BorderLayout.NORTH);

        // Presets

        JLabel lblNewLabel = new JLabel("Safe presets: ");
        panelNorth.add(lblNewLabel);

        JRadioButton radioButtonpresetA = new JRadioButton("A: Eng");
        panelNorth.add(radioButtonpresetA);

        JRadioButton radioButtonpresetB = new JRadioButton("B: Eng");
        panelNorth.add(radioButtonpresetB);

        JRadioButton radioButtonpresetC = new JRadioButton("C Med");
        panelNorth.add(radioButtonpresetC);

        JRadioButton radioButtonpresetD = new JRadioButton("D: Med");
        panelNorth.add(radioButtonpresetD);

        JPanel panelEast = new JPanel();
        contentPane.add(panelEast, BorderLayout.EAST);

        panelWest = new JPanel();
        contentPane.add(panelWest, BorderLayout.WEST);
        GridBagLayout gbl_panelWest = new GridBagLayout();
        gbl_panelWest.columnWidths = new int[]{0, 0};
        gbl_panelWest.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panelWest.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panelWest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelWest.setLayout(gbl_panelWest);

        // Creates a label and all visual CheckBoxes on the left
        // I could have created a for loop to generate them, but I decided not to,
        // because they would be annoymous objects then, which I don't really like to work with

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

        // Layout Stuff:

        panelEast_1 = new JPanel();
        contentPane.add(panelEast_1, BorderLayout.EAST);
        GridBagLayout gbl_panelEast = new GridBagLayout();
        gbl_panelEast.columnWidths = new int[]{0, 20};
        gbl_panelEast.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30};
        gbl_panelEast.columnWeights = new double[]{0.0, 0.0};
        gbl_panelEast.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelEast_1.setLayout(gbl_panelEast);

        // Sets up JLabels on the right for the Skills and Times

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
        lblpsIronflesh.setForeground(red); // Set text color to red
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
        lblpsPowerStrike.setForeground(red); // Set text color to red
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
        lblpsPowerThrow.setForeground(red); // Set text color to red
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
        lblpsPowerDraw.setForeground(red); // Set text color to red
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
        lblpsWeaponMaster.setForeground(red); // Set text color to red
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
        lblpsShield.setForeground(gray); // Set text color to gray, since it's not covered
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
        lblpsAthletics.setForeground(red); // Set text color to red
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
        lblpsRiding.setForeground(red); // Set text color to red
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
        lblpsHorseArchery.setForeground(red); // Set text color to red
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
        lblpsLooting.setForeground(gray); // Set text color to gray, since it's not covered
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
        lblpsTrainer.setForeground(red); // Set text color to red
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
        lblpsTracking.setForeground(red); // Set text color to red
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
        lblpsTactics.setForeground(red); // Set text color to red
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
        lblpsPathFinding.setForeground(red); // Set text color to red
        panelEast_1.add(lblpsPathFinding, gbc_lblpsPathFinding);

        JLabel label_timesPathFinding = new JLabel("0");
        GridBagConstraints gbc_label_timesPathFinding = new GridBagConstraints();
        gbc_label_timesPathFinding.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesPathFinding.gridx = 1;
        gbc_label_timesPathFinding.gridy = 15;
        panelEast_1.add(label_timesPathFinding, gbc_label_timesPathFinding);

        JLabel lblpsSpotting = new JLabel("(PA/I) Spotting");
        GridBagConstraints gbc_lblpsSpotting2 = new GridBagConstraints();
        gbc_lblpsSpotting2.anchor = GridBagConstraints.WEST;
        gbc_lblpsSpotting2.insets = new Insets(0, 0, 5, 5);
        gbc_lblpsSpotting2.gridx = 0;
        gbc_lblpsSpotting2.gridy = 16;
        lblpsSpotting.setForeground(red); // Set text color to red
        panelEast_1.add(lblpsSpotting, gbc_lblpsSpotting2);

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
        lblpsInventoryManagement.setForeground(gray); // Set text color to gray, since it's not covered
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
        lblpsWoundTreatment.setForeground(red); // Set text color to red
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
        lblpsSurgery.setForeground(red); // Set text color to red
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
        lblpsFirstAid.setForeground(red); // Set text color to red
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
        lblpsEngineer.setForeground(red); // Set text color to red
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
        lblpsPersuasion.setForeground(gray); // Set text color to gray, since it's not covered
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
        lblpsPrisonerManagement.setForeground(gray); // Set text color to gray, since it's not covered
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
        lblpsLeadership.setForeground(gray); // Set text color to gray, since it's not covered
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
        lblpsTrade.setForeground(red); // Set text color to red
        panelEast_1.add(lblpsTrade, gbc_lblpsTrade);

        JLabel label_timesTrader = new JLabel("0");
        GridBagConstraints gbc_label_timesTrader = new GridBagConstraints();
        gbc_label_timesTrader.insets = new Insets(0, 0, 5, 0);
        gbc_label_timesTrader.gridx = 1;
        gbc_label_timesTrader.gridy = 25;
        panelEast_1.add(label_timesTrader, gbc_label_timesTrader);


// Add action listeners for the checkboxes, ActionListeners dictate what the GUI elements does upon interaction

        // Create a common action listener template
        ActionListener checkBoxActionListener = e -> {
            JCheckBox checkBox = (JCheckBox) e.getSource(); // Get the source of the event
            String companionName = checkBox.getText();
            CompanionVanilla companion = vanillaTableModel.getCompanionByName(companionName);

            boolean isSelected = checkBox.isSelected();
            companion.setvIsChoosen(isSelected);
            vanillaTableModel.setRowBackgroundColor();

            // Extract data from the JTable and create the list (but smol)
            ArrayList<CompanionVanillaSmol> companionVanillaSmolList = new ArrayList<>();
            for (int row = 0; row < vanillaTableModel.getRowCount(); row++) {
                boolean smolChoosen = vanillaTableModel.getValueAt(row, 0).toString().equals("X");
                String smolName = vanillaTableModel.getValueAt(row, 1).toString();
                String smolSkills = vanillaTableModel.getValueAt(row, 7).toString();

                CompanionVanillaSmol companionRow = new CompanionVanillaSmol(smolChoosen, smolName, smolSkills);
                companionVanillaSmolList.add(companionRow);
            }

            for (int i = 0; i < numSkills; i++) {
                skillCounts[i] = 0;
            }

            // This code block updates a series of labels with the corresponding values from the "skillCounts" array.
            // Each label represents a specific skill, and the code sets the text of each label to the value of the corresponding skill in the array.

            label_timesIronflesh.setText(String.valueOf(skillCounts[Skills.IRONFLESH.getValue()]));
            label_timesPowerStrike.setText(String.valueOf(skillCounts[Skills.POWERSTRIKE.getValue()]));
            label_timesPowerThrow.setText(String.valueOf(skillCounts[Skills.POWERTHROW.getValue()]));
            label_timesPowerDraw.setText(String.valueOf(skillCounts[Skills.POWERDRAW.getValue()]));
            label_timesWeaponMaster.setText(String.valueOf(skillCounts[Skills.WEAPONMASTER.getValue()]));
            label_timesShield.setText(String.valueOf(skillCounts[Skills.SHIELD.getValue()]));
            label_timesAthletics.setText(String.valueOf(skillCounts[Skills.ATHLETICS.getValue()]));
            label_timesRiding.setText(String.valueOf(skillCounts[Skills.RIDING.getValue()]));
            label_timesHorseArchery.setText(String.valueOf(skillCounts[Skills.HORSEARCHERY.getValue()]));
            label_timesLooting.setText(String.valueOf(skillCounts[Skills.LOOTING.getValue()]));
            label_timesTrainer.setText(String.valueOf(skillCounts[Skills.TRAINER.getValue()]));
            label_timesTracking.setText(String.valueOf(skillCounts[Skills.TRACKING.getValue()]));
            label_timesTactics.setText(String.valueOf(skillCounts[Skills.TACTICS.getValue()]));
            label_timesPathFinding.setText(String.valueOf(skillCounts[Skills.PATHFINDING.getValue()]));
            label_timesSpotting.setText(String.valueOf(skillCounts[Skills.SPOTTING.getValue()]));
            label_timesInventoryManagement.setText(String.valueOf(skillCounts[Skills.INVENTORYMANAGEMENT.getValue()]));
            label_timesWoundTreatment.setText(String.valueOf(skillCounts[Skills.WOUNDTREATMENT.getValue()]));
            label_timesWoundSurgery.setText(String.valueOf(skillCounts[Skills.SURGERY.getValue()]));
            label_timesFirstAid.setText(String.valueOf(skillCounts[Skills.FIRSTAID.getValue()]));
            label_timesEngineer.setText(String.valueOf(skillCounts[Skills.ENGINEER.getValue()]));
            label_timesPersuasion.setText(String.valueOf(skillCounts[Skills.PERSUASION.getValue()]));
            label_timesPrisionerManagement.setText(String.valueOf(skillCounts[Skills.PRISONERMANAGEMENT.getValue()]));
            label_timesLeadership.setText(String.valueOf(skillCounts[Skills.LEADERSHIP.getValue()]));
            label_timesTrader.setText(String.valueOf(skillCounts[Skills.TRADE.getValue()]));

            lblpsIronflesh.setForeground(red);
            lblpsPowerStrike.setForeground(red);
            lblpsPowerThrow.setForeground(red);
            lblpsPowerDraw.setForeground(red);
            lblpsWeaponMaster.setForeground(red);
            lblpsShield.setForeground(gray);
            lblpsAthletics.setForeground(red);
            lblpsRiding.setForeground(red);
            lblpsHorseArchery.setForeground(red);
            lblpsLooting.setForeground(gray);
            lblpsTrainer.setForeground(red);
            lblpsTracking.setForeground(red);
            lblpsTactics.setForeground(red);
            lblpsPathFinding.setForeground(red);
            lblpsSpotting.setForeground(red);
            lblpsInventoryManagement.setForeground(gray);
            lblpsWoundTreatment.setForeground(red);
            lblpsSurgery.setForeground(red);
            lblpsFirstAid.setForeground(red);
            lblpsEngineer.setForeground(red);
            lblpsPersuasion.setForeground(gray);
            lblpsPrisonerManagement.setForeground(gray);
            lblpsLeadership.setForeground(gray);
            lblpsTrade.setForeground(red);

            for (CompanionVanillaSmol smol : companionVanillaSmolList) {
                String smolSkills = smol.getvSkills();

                if (smol.isvIsChoosen()) {

                    if (smolSkills.contains("Ironflesh")) {
                        skillCounts[Skills.IRONFLESH.getValue()]++;
                        label_timesIronflesh.setText(String.valueOf(skillCounts[Skills.IRONFLESH.getValue()]));
                        lblpsIronflesh.setForeground(green);
                    }

                    if (smolSkills.contains("Power Strike")) {
                        skillCounts[Skills.POWERSTRIKE.getValue()]++;
                        label_timesPowerStrike.setText(String.valueOf(skillCounts[Skills.POWERSTRIKE.getValue()]));
                        lblpsPowerStrike.setForeground(green);
                    }

                    if (smolSkills.contains("Power Throw")) {
                        skillCounts[Skills.POWERTHROW.getValue()]++;
                        label_timesPowerThrow.setText(String.valueOf(skillCounts[Skills.POWERTHROW.getValue()]));
                        lblpsPowerThrow.setForeground(green);
                    }

                    if (smolSkills.contains("Power Draw")) {
                        skillCounts[Skills.POWERDRAW.getValue()]++;
                        label_timesPowerDraw.setText(String.valueOf(skillCounts[Skills.POWERDRAW.getValue()]));
                        lblpsPowerDraw.setForeground(green);
                    }

                    if (smolSkills.contains("Weapon Master")) {
                        skillCounts[Skills.WEAPONMASTER.getValue()]++;
                        label_timesWeaponMaster.setText(String.valueOf(skillCounts[Skills.WEAPONMASTER.getValue()]));
                        lblpsWeaponMaster.setForeground(green);
                    }

                    if (smolSkills.contains("Shield")) {
                        skillCounts[Skills.SHIELD.getValue()]++;
                        label_timesShield.setText(String.valueOf(skillCounts[Skills.SHIELD.getValue()]));
                        lblpsShield.setForeground(green);
                    }

                    if (smolSkills.contains("Athletics")) {
                        skillCounts[Skills.ATHLETICS.getValue()]++;
                        label_timesAthletics.setText(String.valueOf(skillCounts[Skills.ATHLETICS.getValue()]));
                        lblpsAthletics.setForeground(green);
                    }
                    if (smolSkills.contains("Riding")) {
                        skillCounts[Skills.RIDING.getValue()]++;
                        label_timesRiding.setText(String.valueOf(skillCounts[Skills.RIDING.getValue()]));
                        lblpsRiding.setForeground(green);
                    }
                    if (smolSkills.contains("Horse Archery")) {
                        skillCounts[Skills.HORSEARCHERY.getValue()]++;
                        label_timesHorseArchery.setText(String.valueOf(skillCounts[Skills.HORSEARCHERY.getValue()]));
                        lblpsHorseArchery.setForeground(green);
                    }
                    if (smolSkills.contains("Looting")) {
                        skillCounts[Skills.LOOTING.getValue()]++;
                        label_timesLooting.setText(String.valueOf(skillCounts[Skills.LOOTING.getValue()]));
                        lblpsLooting.setForeground(green);
                    }
                    if (smolSkills.contains("Trainer")) {
                        skillCounts[Skills.TRAINER.getValue()]++;
                        label_timesTrainer.setText(String.valueOf(skillCounts[Skills.TRAINER.getValue()]));
                        lblpsTrainer.setForeground(green);
                    }
                    if (smolSkills.contains("Tracking")) {
                        skillCounts[Skills.TRACKING.getValue()]++;
                        label_timesTracking.setText(String.valueOf(skillCounts[Skills.TRACKING.getValue()]));
                        lblpsTracking.setForeground(green);
                    }
                    if (smolSkills.contains("Tactics")) {
                        skillCounts[Skills.TACTICS.getValue()]++;
                        label_timesTactics.setText(String.valueOf(skillCounts[Skills.TACTICS.getValue()]));
                        lblpsTactics.setForeground(green);
                    }
                    if (smolSkills.contains("Pathfinding")) {
                        skillCounts[Skills.PATHFINDING.getValue()]++;
                        label_timesPathFinding.setText(String.valueOf(skillCounts[Skills.PATHFINDING.getValue()]));
                        lblpsPathFinding.setForeground(green);
                    }
                    if (smolSkills.contains("Spotting")) {
                        skillCounts[Skills.SPOTTING.getValue()]++;
                        label_timesSpotting.setText(String.valueOf(skillCounts[Skills.SPOTTING.getValue()]));
                        lblpsSpotting.setForeground(green);
                    }
                    if (smolSkills.contains("Inventory Management")) {
                        skillCounts[Skills.INVENTORYMANAGEMENT.getValue()]++;
                        label_timesInventoryManagement.setText(String.valueOf(skillCounts[Skills.INVENTORYMANAGEMENT.getValue()]));
                        lblpsInventoryManagement.setForeground(green);
                    }
                    if (smolSkills.contains("Wound Treatment")) {
                        skillCounts[Skills.WOUNDTREATMENT.getValue()]++;
                        label_timesWoundTreatment.setText(String.valueOf(skillCounts[Skills.WOUNDTREATMENT.getValue()]));
                        lblpsWoundTreatment.setForeground(green);
                    }
                    if (smolSkills.contains("Surgery")) {
                        skillCounts[Skills.SURGERY.getValue()]++;
                        label_timesWoundSurgery.setText(String.valueOf(skillCounts[Skills.SURGERY.getValue()]));
                        lblpsSurgery.setForeground(green);
                    }
                    if (smolSkills.contains("First Aid")) {
                        skillCounts[Skills.FIRSTAID.getValue()]++;
                        label_timesFirstAid.setText(String.valueOf(skillCounts[Skills.FIRSTAID.getValue()]));
                        lblpsFirstAid.setForeground(green);
                    }
                    if (smolSkills.contains("Engineer")) {
                        skillCounts[Skills.ENGINEER.getValue()]++;
                        label_timesEngineer.setText(String.valueOf(skillCounts[Skills.ENGINEER.getValue()]));
                        lblpsEngineer.setForeground(green);
                    }
                    if (smolSkills.contains("Persuasion")) {
                        skillCounts[Skills.PERSUASION.getValue()]++;
                        label_timesPersuasion.setText(String.valueOf(skillCounts[Skills.PERSUASION.getValue()]));
                        lblpsPersuasion.setForeground(green);
                    }
                    if (smolSkills.contains("Prisoner Management")) {
                        skillCounts[Skills.PRISONERMANAGEMENT.getValue()]++;
                        label_timesPrisionerManagement.setText(String.valueOf(skillCounts[Skills.PRISONERMANAGEMENT.getValue()]));
                        lblpsPrisonerManagement.setForeground(green);
                    }
                    if (smolSkills.contains("Leadership")) {
                        skillCounts[Skills.LEADERSHIP.getValue()]++;
                        label_timesLeadership.setText(String.valueOf(skillCounts[Skills.LEADERSHIP.getValue()]));
                        lblpsLeadership.setForeground(green);
                    }
                    if (smolSkills.contains("Trade")) {
                        skillCounts[Skills.TRADE.getValue()]++;
                        label_timesTrader.setText(String.valueOf(skillCounts[Skills.TRADE.getValue()]));
                        lblpsTrade.setForeground(green);
                    }
                }




            }

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

        // to hide not relevant Skills for your party
        chckbxOnlyPartyskillsRelevant.addActionListener(e -> {
            if (chckbxOnlyPartyskillsRelevant.isSelected()) {
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
            } else {
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

        radioButtonpresetA.addActionListener(e -> {
            radioButtonpresetB.setSelected(false);
            radioButtonpresetC.setSelected(false);
            radioButtonpresetD.setSelected(false);

            buttonReset.doClick();

            artimennerCheckBox.doClick();
            borchaCheckBox.doClick();
            matheldCheckBox.doClick();
            lezalitCheckBox.doClick();
            rolfCheckBox.doClick();

            alayenCheckBox.doClick();
            baheshturCheckBox.doClick();
            firentisCheckBox.doClick();

        });

        radioButtonpresetB.addActionListener(e -> {
            radioButtonpresetA.setSelected(false);
            radioButtonpresetC.setSelected(false);
            radioButtonpresetD.setSelected(false);

            buttonReset.doClick();

            artimennerCheckBox.doClick();
            borchaCheckBox.doClick();
            matheldCheckBox.doClick();
            lezalitCheckBox.doClick();
            rolfCheckBox.doClick();

            katrinCheckBox.doClick();
            marnidCheckBox.doClick();
            nizarCheckBox.doClick();


        });

        radioButtonpresetC.addActionListener(e -> {
            radioButtonpresetA.setSelected(false);
            radioButtonpresetB.setSelected(false);
            radioButtonpresetD.setSelected(false);

            buttonReset.doClick();

            bundukCheckBox.doClick();
            deshaviCheckBox.doClick();
            klethiCheckBox.doClick();
            jeremusCheckBox.doClick();
            ymiraCheckBox.doClick();

            alayenCheckBox.doClick();
            baheshturCheckBox.doClick();
            firentisCheckBox.doClick();


        });

        radioButtonpresetD.addActionListener(e -> {
            radioButtonpresetB.setSelected(false);
            radioButtonpresetC.setSelected(false);
            radioButtonpresetA.setSelected(false);

            buttonReset.doClick();

            bundukCheckBox.doClick();
            deshaviCheckBox.doClick();
            klethiCheckBox.doClick();
            jeremusCheckBox.doClick();
            ymiraCheckBox.doClick();

            katrinCheckBox.doClick();
            marnidCheckBox.doClick();
            nizarCheckBox.doClick();

        });

        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JCheckBox[] checkBoxes = {
                        deshaviCheckBox, firentisCheckBox, jeremusCheckBox, marnidCheckBox, ymiraCheckBox,
                        katrinCheckBox, bundukCheckBox, klethiCheckBox, alayenCheckBox, artimennerCheckBox,
                        borchaCheckBox, nizarCheckBox, rolfCheckBox, baheshturCheckBox, lezalitCheckBox, matheldCheckBox
                };

                for (JCheckBox checkBox : checkBoxes) {
                    if(checkBox.isSelected()){
                        checkBox.doClick();
                        // kinda primitive, but i like the animation
                    }
                }

            }
        });

        buttonToolInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "Companion Harmony Visualizer Tool by AL2D\n\n" +
                        "Empowering Your Mount & Blade: Warband Journey\n\n" +
                        "Introduction:\n\n" +
                        "Greetings, traveler of the virtual realm.\n" +
                        "In the spirit of self-improvement, and driven by the desire to explore the world of programming,\n" +
                        "I, AL2D, crafted the Companion Harmony Visualizer Tool in October 2023.\n" +
                        "As a novice programmer, this project served as a personal challenge and a stepping stone in my journey through the realms of software development.\n\n" +
                        "Reach Out and Connect:\n\n" +
                        "Should you have questions or seek to engage in insightful discussions,\n" +
                        "I encourage you to utilize the GitHub message discussion tab.\n" +
                        "Alternatively, you can find me on Discord at AL2D#3015.\n\n" +
                        "About the Tool:\n\n" +
                        "In the world of Mount & Blade: Warband, the role of companions extends beyond mere story characters.\n" +
                        "They are the backbone of your party, and their skills can make or break your journey.\n" +
                        "If you've ever found yourself frustrated by the bickering of your companions,\n" +
                        "akin to unruly schoolchildren, and longed for a solution to the puzzle of creating the perfect party,\n" +
                        "look no further. The Companion Harmony Visualizer Tool is your answer.\n\n" +
                        "What Does It Offer?\n\n" +
                        "This tool provides you with the ability to construct your ideal companion combination.\n" +
                        "It shifts the focus from the companions themselves as mere story characters to their invaluable role as your skillful backpack.\n" +
                        "Say goodbye to the chaos and confusion of companion interactions and \n" +
                        "hello to the creation of a harmonious and effective party that caters to your unique playstyle.\n\n" +
                        "Why Choose This Tool?\n\n" +
                        "- Streamlined Experience: With our user-friendly interface, you can effortlessly select companions and gain insight into their interactions and initial skills.\n" +
                        "- Comprehensive Insights: Our tool provides you with in-depth explanations and the crucial ability to assess your party's harmony.\n" +
                        "- Practicality: The 'Export' feature enables you to save your selected companions' details as a convenient txt file for reference.\n\n" +
                        "Your Journey Begins with a less bitchy companion party.";

                JTextArea textArea = new JTextArea(20, 60);
                Font verdanaFont = new Font("Verdana", Font.PLAIN, 14); // Change 14 to your preferred font size
                textArea.setFont(verdanaFont);
                textArea.setText(message);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setCaretPosition(0);

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                // Display the message in a dialog
                JOptionPane.showMessageDialog(null, scrollPane, "Companion Harmony Visualizer Tool", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonCompanionInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a JTextArea to display the text
                JTextArea textArea = new JTextArea(20, 60); // Rows x Columns
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setMargin(new Insets(10, 10, 10, 10));
                textArea.setEditable(false);

                // Create a JScrollPane to make the text scrollable
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                // Add the text to the JTextArea
                String companionGuideText = "Companions Compatibility Guide\n\n"
                        + "In the world of Mount & Blade: Warband, heroes, often referred to as companions, come with their unique traits and preferences. When assessing compatibility, we distinguish between two categories:\n\n"
                        + "- Green: Compatible Companions\n"
                        + "- Red: Incompatible Companions\n\n"
                        + "While I commonly refer to them as companions, it's essential to note that they are officially known as heroes in the game.\n\n"
                        + "About Heroes or Companions:\n\n"
                        + "Heroes or companions are unique troops with individual names, stories, skills, attributes, and equipment, all of which can be customized by you. Similar to the player character, monarchs, and vassals, heroes never meet their demise, only falling unconscious. Garrisons can never be their station, and in all games except for Mount & Blade II: Bannerlord, companions remain invincible.\n\n"
                        + "Each hero has a preference, liking one other hero and disliking two others, often expressing their opinions after battles or during your journey. They also have preferences for certain actions and might engage with you if they disapprove of your choices, such as failing quests or running low on provisions.\n\n"
                        + "Recruitment of Companions:\n\n"
                        + "Companions can be found in taverns scattered throughout Calradia, but their locations are subject to change. Some may require a fee ranging from 100 to 500 denars to join your party, while five companions are willing to join you without charge. Regardless of the recruitment cost, they all demand wages based on their level. As there is no strict level cap for companions, their wages can exceed those of any other troop in the game as they level up.\n\n"
                        + "Ways of Temporary Departure:\n\n"
                        + "There are several scenarios in which you might temporarily lose a hero:\n\n"
                        + "- If you voluntarily part ways with a hero, you can later pick them up again without a recruitment cost. Asking a traveler in a tavern can provide information about the whereabouts of any hero who was once in your group.\n"
                        + "- If you're taken prisoner, you'll need to wait for your freedom. Upon release, some heroes might be set free with you, while others might be captured. You can either pay a Ransom Broker to free them for a fee (they will join you within 1-2 days), or you can rescue them as you would with any other prisoner. If a hero escapes or is released due to a peace treaty, you can find them in taverns as usual.\n"
                        + "- Keeping heroes with mutual dislikes in the same group can lead to their departure as they decide to 'go back home' or 'settle down.' They can not be found via traveler as they do not respawn right away. Instead, it takes several weeks before they respawn, and you may have trouble finding them, which could be related to your renown. Some companions who left voluntarily may not reappear until your renown surpasses the level at which they departed by around 100 points. A traveler should be able to guide you to their location after this period.\n"
                        + "- If you lend a hero to a lord via the Lend Companion quest, you can lose contact with them if that lord becomes hostile towards you or pledges loyalty to another faction. In such cases, you won't be able to reunite with that hero until several in-game weeks have passed. During this period, a traveler won't have information about their location, but the lost hero will eventually seek you out.\n\n"
                        + "List of Heroes:\n\n"
                        + "There are a total of 16 companions in the game, each with their likes, dislikes, recruitment costs, and specialized skills. The 'Skills' column displays each hero's primary skills, typically at level 3.\n\n"
                        + "In Mount & Blade: Warband, you have the option to appoint your companions as lords, granting them towns, villages, or castles. However, be cautious when choosing companions for this role, as other vassals may be displeased with commoners holding such positions. Ideally, noble companions, such as Matheld, Lezalit, Rolf, Alayen, Baheshtur, and Firentis, are better suited for becoming vassals. If a hero becomes a lord and later defects from your faction, they can be found in taverns and re-recruited to your party. They will maintain their unique coat of arms, title, and the equipment they had as a lord.\n\n"
                        + "A Rare Opportunity: Noble Companions\n\n"
                        + "An exceptionally rare situation involving noble companions occurs when a lord is accused of treason while they are a prisoner. If the accused lord goes into exile instead of joining another faction, they remain imprisoned by their captors. In this unique circumstance, you can secure their freedom and attempt to recruit them into your party as a companion. If the lord accepts your offer, they will join your party like any other companion. However, this feature may not be entirely implemented, as some regular party dialogue options may be missing lines. Additionally, equipping them might involve a slight bug; you may need to enter the equipment menu, exit, and then re-enter it to properly display their inventory. You can reinstate them into the nobility by granting them a fief, but their female relatives, such as wives and daughters, may remain in a ransom dialog.\n\n"
                        + "Dislike as Emissary:\n\n"
                        + "In Warband, you have the option to send heroes on diplomatic missions to enhance your right to rule. However, this may negatively affect other heroes' opinions. The 'dislike as emissary' system follows a specific loop: each hero in the list dislikes the one immediately following them as an emissary.\n\n"
                        + "Alayen → Baheshtur → Ymira → Deshavi → Bunduk → Nizar → Jeremus → Klethi → Marnid → Matheld → Firentis → Rolf → Artimenner → Katrin → Lezalit → Borcha → Alayen\n\n"
                        + "Information primarily sourced from: Mount & Blade Wiki - Heroes (https://mountandblade.fandom.com/wiki/Heroes)";

                textArea.setText(companionGuideText);
                Font verdanaFont = new Font("Verdana", Font.PLAIN, 14);
                textArea.setFont(verdanaFont);

                // Show the dialog with the text
                JOptionPane.showMessageDialog(null, scrollPane, "Companion Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });




        buttonSkillInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a JTextArea to display the text
                JTextArea textArea = new JTextArea(20, 60); // Rows x Columns
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setMargin(new Insets(10, 10, 10, 10));
                textArea.setEditable(false);

                // Create a JScrollPane to make the text scrollable
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                // Add the text to the JTextArea
                textArea.setText("Skill Proficiency Overview\n\n" +
                        "Skills are portrayed on the right side, using the following color code:\n" +
                        "Green: Skill is covered by at least one party member.\n" +
                        "Red: Skill is not covered by any party member.\n" +
                        "Yellow: Skill is not possible to be covered by the current party.\n" +
                        "Please remember that theoretically, you could teach each companion any skill. The display on the right shows who has a headstart, but every companion can learn any skill according to your preferences.\n\n" +
                        "Skills in Mount & Blade: Warband\n\n" +
                        "In Mount & Blade: Warband, skills grant specific abilities and buffs to characters and parties. The effectiveness of a skill is determined by its level, with a maximum skill level of 10. There are 24 skills in total.\n\n" +
                        "Skill Levels and Base Attributes:\n\n" +
                        "Each skill has a corresponding base attribute.\n" +
                        "A character's skill level cannot exceed one-third of their level in the base attribute (except with the use of books).\n" +
                        "For example, Tactics has Intelligence as its base attribute, so if Intelligence is level 9, Tactics cannot surpass level 3.\n" +
                        "Initial skill levels for players are determined by their character's background and may exceed this limitation.\n\n" +
                        "Skill Types:\n\n" +
                        "Skills come in three types:\n" +
                        "Party Skill: Affects the entire party. The skill level is determined by the party member with the highest level in that skill.\n" +
                        "Leader Skill: Similar to party skills but only the party leader's level in the skill is used.\n" +
                        "Personal Skill: Impacts an individual party leader or companion. Generally, personal skills benefit only the individual.\n\n" +
                        "Party Leader Skill Bonus:\n" +
                        "A party skill's effectiveness is a combination of the skill level of the person with the best rank in it and a bonus based on your own rank in that skill.\n" +
                        "The party skill bonus is applied even if you have the highest rank in the skill.\n\n" +
                        "Please note that although Looting is a party skill, the player should level it to be effective." +
                        "Information primarily sourced from: Mount & Blade Wiki - Skills (https://mountandblade.fandom.com/wiki/Skills)");
                Font verdanaFont = new Font("Verdana", Font.PLAIN, 14);
                textArea.setFont(verdanaFont);

                // Show the dialog with the text
                JOptionPane.showMessageDialog(null, scrollPane, "Skill Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menuItemAbout.addActionListener(e ->{
            JOptionPane.showMessageDialog(null, "Companion Harmony Visualizer Tool for Mount and Blade Warband Vanilla coded by AL2D in Oktober 2023 (Dc: AL2D#3015) \n Official Website: https://github.com/al2d-x/Companion-Harmony-Visualizer-Tool-by-AL2D", "About", JOptionPane.INFORMATION_MESSAGE);
        });

        // Used to create the exported file... here I was actually helped a lot by CHAT GPT for the String Builder since it kinda confused me
        menuItemExport.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int userChoice = fc.showSaveDialog(null);

            if (userChoice == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();

                if (!file.getName().toLowerCase().endsWith(".txt")) {
                    file = new File(file.getParentFile(), file.getName() + ".txt");
                }

                try (FileWriter fw = new FileWriter(file);
                     BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write("------ COMPANIONS CHOSEN ------");
                    bw.newLine();

                    for (int row = 0; row < vanillaTableModel.getRowCount(); row++) {
                        boolean choosen = "X".equals(vanillaTableModel.getValueAt(row, 0));
                        String name = vanillaTableModel.getValueAt(row, 1).toString();
                        String hates = vanillaTableModel.getValueAt(row, 5).toString();
                        boolean isNoble = "X".equals(vanillaTableModel.getValueAt(row, 6));
                        String isNobleString = isNoble ? "Yes" : "No";
                        String skills = vanillaTableModel.getValueAt(row, 7).toString();

                        if (choosen) {
                            StringBuilder sum = new StringBuilder()
                                    .append("Companion Name: ").append(name).append("\n")
                                    .append("Is Noble: ").append(isNobleString).append("\n")
                                    .append("Skills: ").append(skills).append("\n")
                                    .append("Hate: ").append(hates).append("\n");

                            bw.write(sum.toString());
                            bw.newLine();
                        }
                    }

                    bw.newLine();
                    bw.write("------ PARTY SKILLS COVERED AT THE BEGINNING ------");
                    bw.newLine();

                    String[] skillNames = {
                            "Trainer", "Tracking", "Tactics", "Pathfinding",
                            "Spotting", "Wound Treatment", "Surgery",
                            "First Aid", "Engineer", "Trade"
                    };

                    for (int i = 0; i < skillNames.length; i++) {
                        String skillName = skillNames[i];
                        int skillValue = skillCounts[i];
                        String yesNo = (skillValue != 0) ? "Yes" : "No";

                        StringBuilder skillInfo = new StringBuilder()
                                .append(skillName).append(": ").append(yesNo);

                        if (yesNo.equals("Yes")) {
                            skillInfo.append(" | Times: ").append(skillValue);
                        }

                        bw.write(skillInfo.toString());
                        bw.newLine();
                    }
                    bw.newLine();
                    bw.newLine();
                    bw.write("----- INFO ABOUT FILE -----");
                    bw.newLine();
                    bw.write("This Exported File was auto-generated by the Companion Harmony Visualizer Tool available on GitHub:");
                    bw.newLine();
                    bw.write("https://github.com/al2d-x/Companion-Harmony-Visualizer-Tool-by-AL2D");

                    JOptionPane.showMessageDialog(null, "File created successfully: " + file.getName(), "File Created", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error creating the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // I build this in kinda for fun
        menuItemExit.addActionListener(e -> {
            String formattedText =
                            "$$$$$$$\\                      \n" +
                            "$$  __$$\\                     \n" +
                            "$$ |  $$ |$$\\   $$\\  $$$$$$\\  \n" +
                            "$$$$$$$\\ |$$ |  $$ |$$  __$$\\ \n" +
                            "$$  __$$\\ $$ |  $$ |$$$$$$$$ |\n" +
                            "$$ |  $$ |$$ |   $$ |$$   ____|\n" +
                            "$$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ \n" +
                            "\\_______/  \\___$$ | \\_______|\n" +
                            "            $$\\   $$ |          \n" +
                            "            \\$$$$$$  |          \n" +
                            "             \\______/           \n";

            JOptionPane pane = new JOptionPane(formattedText, JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = pane.createDialog("Bye :)");
            dialog.setModal(false);

            Timer timer = new Timer(2000, e1 -> {
                dialog.dispose();
                System.exit(0);
            });
            timer.setRepeats(false);
            timer.start();

            dialog.setVisible(true);
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    /**
     * The original code was authored by AL2D and may be subject to modifications by other users.
     * However, please ensure to attribute the tool abbreviation created to AL2D.
     * Here's a corrected version of your text:
     * "It took me about 10 days to code everything.
     * I invested more time in this tool than one might imagine,
     * but that's probably because I'm not an experienced coder."
     */

    // --- CREDIT ---

    // Original Coder of CHVT: AL2D v1.0.0 (Dc: AL2D #3015)
    // Add your name here if modified by you
    // Add your name here if modified by you
    //...
}

