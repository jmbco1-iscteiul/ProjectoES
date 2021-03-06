package MainWork;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


/**
 * Interface gráfica principal desenvolvida, com todos os botões descritos para Facebook, Twitter e E-mail. Também
 * se apresenta a barra de pesquisa e o botão  respetivo (search).
 * 
 * @author Brogueira
 *
 */
public class Interfacegrafica{

	private JFrame frame;
	private BomDiaAcademia bda;

	private JTextField label = new JTextField();
	private JTextField textface = new JTextField();
	private JTextField textmail = new JTextField();
	private JTextField texttwitter = new JTextField();
	private JTextField textassunto = new JTextField();
	private JTextField textpara = new JTextField();
	private JButton bface = new JButton("TimeLine");
	private JButton bmail = new JButton("TimeLine");
	private JButton btwitter = new JButton("TimeLine");
	private	JButton bfaceprocurar = new JButton("Procurar Palavra");
	private JButton bmailprocurar = new JButton("Procurar Palavra");
	private JButton btwitterprocurar = new JButton("Procurar Palavra");
	private JButton bkeyword = new JButton("Mostrar todas as TimeLine");
	private	JButton bperiodoinfo = new JButton("Período da Informação");
	private	JButton bescreverpost = new JButton("Escrever Post");
	private	JButton bescreverpostf = new JButton("Escrever Post");
	private	JButton bescrevermail = new JButton("Escrever Email");
	private	JButton sair = new JButton("Sair da Aplicação");
	private	DefaultListModel<Mensagem> model = new DefaultListModel<>();
	private	JList<Mensagem> list = new JList<>(model);
	private String tokenface;
	private String tokentwitter1;
	private String tokentwitter2;
	private String tokentwitter3;
	private String tokentwitter4;
	private String contaemail; 
	private String passemail;


	public Interfacegrafica(BomDiaAcademia bda, String tokenface, String tokentwitter1, String tokentwitter2, String tokentwitter3, String tokentwitter4, String contaemail, String passemail) {
		this.bda = bda;
		this.tokenface=tokenface;
		this.tokentwitter1=tokentwitter1;
		this.tokentwitter2=tokentwitter2;
		this.tokentwitter3=tokentwitter3;
		this.tokentwitter4=tokentwitter4;
		this.contaemail=contaemail;
		this.passemail=passemail;



		frame = new JFrame("MAIN MENU");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addFrameContent();
		frame.pack();
		open();


		setTokens();
		if(bda.internetConnection) {
			bda.getFacebook().autenticacao();
			bda.getTwitter().autenticacao();
			bda.getMail().autenticacao();
			bda.getGuarda().guardar();
		}else{
			bda.getTwitter().setModelTwitter(bda.getGuarda().carregar(bda.getUserName()).get(0));
			bda.getFacebook().setModelFace(bda.getGuarda().carregar(bda.getUserName()).get(1));
			bda.getMail().setModelEmail(bda.getGuarda().carregar(bda.getUserName()).get(2));

			bescrevermail.setEnabled(false);
			bescreverpost.setEnabled(false);
			bescreverpostf.setEnabled(false);
			textassunto.setEditable(false);
			textface.setEditable(false);
			textmail.setEditable(false);
			textpara.setEditable(false);
			texttwitter.setEditable(false);
		}

		PeriododaInformacao();
		TodasTimeLines();

		getFaceTimeLine();
		getTwitterTimeLine();
		getEmailTimeLine();

		escreverPostFace();
		escreverPostTwitter();
		escreverMail();

		procurarpalavraFace();
		procurarpalavraTwitter();
		procurarpalavraMail();	

		selectMail();

	}

	/**
	 * Função que abre a janela principal e lhe o tamanha pretendido
	 * @author Brogueira
	 * 
	 */
	public void open() {

		frame.setSize(1000, 900);
		frame.setVisible(true);

	}

	/**
	 * Função que cria a disposição dos botões, da barra e da caixa onde se representa os resultados das pesquisas.
	 *  Cria-se um Layout principal e posteriormente os Layouts secundários.
	 *  @author Brogueira
	 */
	private void addFrameContent() {

		JPanel search = new JPanel(new GridLayout(1, 2));
		JPanel panelp = new JPanel();
		JPanel panelp1 = new JPanel(new GridLayout(1,2));
		JPanel panel16= new JPanel(new GridLayout(6,1));
		JPanel panel15= new JPanel();
		JPanel panel14= new JPanel();
		JPanel panel17= new JPanel(new GridLayout(2,1));
		JPanel panel18= new JPanel();
		JPanel panel1= new JPanel();
		JPanel panel13= new JPanel(new GridLayout(3,1));
		JPanel panel19= new JPanel();
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel20 = new JPanel();
		JPanel panel21 = new JPanel();
		JPanel panel22 = new JPanel();
		Color color= new Color(128,128,255);

		list.setFont(new Font("SansSerif", Font.BOLD, 15));

		Dimension d1= new Dimension(500,30);
		label.setPreferredSize(d1);
		JLabel area3 = new JLabel();
		area3.setText("Escrever palavra que desejar procurar:");

		area3.setFont(new Font("SansSerif", Font.BOLD, 15));
		area3.setForeground(color);
		panelp.add(area3, BorderLayout.CENTER);
		panelp.add(label, BorderLayout.EAST);


		JLabel area1 = new JLabel();
		area1.setText("Filtros:");
		area1.setFont(new Font("SansSerif", Font.BOLD, 30));
		area1.setForeground(color);
		Dimension d10= new Dimension(490,30);
		area1.setPreferredSize(d10);
		panel13.add(area1);



		panel8.add(bkeyword);
		panel1.add(panel8);

		panel9.add(bperiodoinfo);
		panel1.add(panel9);

		panel13.add(panel1);

		JLabel area = new JLabel();
		area.setText("Plataformas Online:");
		area.setFont(new Font("SansSerif", Font.BOLD, 30));
		area.setForeground(color);

		panel13.add(area);
		panel19.add(panel13);
		panel20.add(panel13);
		bface.setFont(new Font("SansSerif", Font.BOLD, 20));
		bmail.setFont(new Font("SansSerif", Font.BOLD, 20));
		btwitter.setFont(new Font("SansSerif", Font.BOLD, 20));
		bfaceprocurar.setFont(new Font("SansSerif", Font.BOLD, 15));
		bmailprocurar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btwitterprocurar.setFont(new Font("SansSerif", Font.BOLD, 15));

		Dimension d5= new Dimension(150,60);
		bface.setPreferredSize(d5);
		bmail.setPreferredSize(d5);
		btwitter.setPreferredSize(d5);

		Dimension d8= new Dimension(180,60);
		bfaceprocurar.setPreferredSize(d8);
		bmailprocurar.setPreferredSize(d8);
		btwitterprocurar.setPreferredSize(d8);

		Dimension d6= new Dimension(365,30);
		textmail.setPreferredSize(d6);
		texttwitter.setPreferredSize(d6);
		textface.setPreferredSize(d6);


		JLabel face = new JLabel();
		face.setText("FACEBOOK:");
		face.setFont(new Font("SansSerif", Font.BOLD, 20));
		face.setForeground(color);
		panel2.add(face);
		panel2.add(bface);
		panel2.add(bfaceprocurar);

		panel14.add(textface, BorderLayout.CENTER);
		panel14.add(bescreverpostf, BorderLayout.EAST);

		panel16.add(panel2);
		panel16.add(panel14);


		JLabel twitter = new JLabel();
		twitter.setText("TWITTER:");
		twitter.setFont(new Font("SansSerif", Font.BOLD, 20));
		twitter.setForeground(color);
		panel3.add(twitter);
		panel3.add(btwitter);
		panel3.add(btwitterprocurar);

		panel15.add(texttwitter, BorderLayout.CENTER);
		panel15.add(bescreverpost, BorderLayout.EAST);

		panel16.add(panel3);
		panel16.add(panel15);

		JLabel mail = new JLabel();
		mail.setText("E-MAIL:");
		mail.setFont(new Font("SansSerif", Font.BOLD, 20));
		mail.setForeground(color);
		panel4.add(mail);
		panel4.add(bmail);
		panel4.add(bmailprocurar);
		panel16.add(panel4);

		panel6.add(textmail, BorderLayout.CENTER);
		panel6.add(bescrevermail, BorderLayout.EAST);
		panel17.add(panel6);

		Dimension d7= new Dimension(190,30);
		textpara.setPreferredSize(d7);
		textassunto.setPreferredSize(d7);

		JLabel area2 = new JLabel();
		area2.setText("Para:");
		area2.setFont(new Font("SansSerif", Font.BOLD, 15));
		area2.setForeground(color);

		JLabel area4 = new JLabel();
		area4.setText("Tema:");
		area4.setFont(new Font("SansSerif", Font.BOLD, 15));
		area4.setForeground(color);

		panel19.add(area4, BorderLayout.CENTER);
		panel19.add(textassunto, BorderLayout.EAST);

		panel19.add(area2, BorderLayout.CENTER);
		panel19.add(textpara, BorderLayout.EAST);


		panel19.add(panel22);
		panel17.add(panel19);

		panel16.add(panel17);

		panel18.add(panel16);


		panel21.add(panel20);
		panel21.add(panel18);

		panelp.add(search);
		panelp1.add(panel21);
		//				panelp2.add(panel6);

		JScrollPane scrollpane1 = new JScrollPane(list);
		panelp1.add(scrollpane1);

		frame.add(panelp,BorderLayout.NORTH);
		frame.add(panelp1);

	}


	/**
	 * Função que vai buscar a timeline do facebook e a representa na janela de visualização
	 * @author Brogueira
	 */

	public void setTokens() {
		bda.getFacebook().setToken(tokenface);
		bda.getTwitter().setTokentwitter1(tokentwitter1);
		bda.getTwitter().setTokentwitter2(tokentwitter2);
		bda.getTwitter().setTokentwitter3(tokentwitter3);
		bda.getTwitter().setTokentwitter4(tokentwitter4);
		bda.getMail().setMail(contaemail);
		bda.getMail().setPass(passemail);
	}

	/**
	 * Função que apresenta a timeline do facebook na frame
	 * @author Brogueira
	 */

	public void getFaceTimeLine() {
		bface.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				setModel(bda.getFacebook().getModelFace());
			}
		});
	}

	/**
	 * Função que vai buscar todas as timelines e as representa na janela de visualização
	 * @author Brogueira
	 */	

	public void TodasTimeLines() {
		bkeyword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();

				for(int i = 0; i<bda.getFacebook().getModelFace().size();i++) {
					model.addElement(bda.getFacebook().getModelFace().get(i));
				}
				for(int i = 0; i<bda.getTwitter().getModelTwitter().size();i++) {
					model.addElement(bda.getTwitter().getModelTwitter().get(i));
				}

				for(int i = 0; i<bda.getMail().getModelEmail().size();i++) {
					model.addElement(bda.getMail().getModelEmail().get(i));
				}

				setModel(model);

			}
		});
	}

	/**
	 * Função que vai buscar, dado um número de dias, as timelines e as representa na janela de visualização
	 * @author Brogueira
	 */	
	public void PeriododaInformacao() {
		bperiodoinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				InterfacePeriododaInformacao d = new InterfacePeriododaInformacao(bda, model);
				d.open();

			}
		});
	}

	/**
	 * Função que vai buscar a timeline do twitter e a representa na janela de visualização
	 * @author Brogueira
	 */

	public void getTwitterTimeLine() {
		btwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				bda.getTwitter().getTimeline(model);
				setModel(model);
			}
		});
	}

	/**
	 * Função que vai buscar a timeline do email e a representa na janela de visualização
	 * @author Brogueira
	 */

	public void getEmailTimeLine() {
		bmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				bda.getMail().caixaChegada(model);
				setModel(model);
			}
		});
	}

	/**
	 * Função que permite inserir o conteúdo na interface gráfica para fazer posts no facebook
	 *  @author Brogueira
	 */

	public void escreverPostFace() {
		bescreverpostf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				String procurar=textface.getText();
				bda.getFacebook().makePost(procurar);
				textface.setText("");
			}
		});
	}

	/**
	 * Função que permite inserir o conteúdo na interface gráfica para fazer tweets no Twitter
	 * @author Brogueira
	 */

	public void escreverPostTwitter() {
		bescreverpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				String procurar=texttwitter.getText();
				System.out.println(procurar);
				bda.getTwitter().updateStatus(procurar);
				texttwitter.setText("");
			}
		});
	}

	/**
	 * Função que permite inserir o conteúdo na interface gráfica para fazer escrever novos emails
	 * @author Brogueira
	 */

	public void escreverMail() {
		bescrevermail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				bda.getMail().sendMail(textpara.getText(),textmail.getText(),textassunto.getText());
				textmail.setText("");
				textassunto.setText("");
				textpara.setText("");
			}
		});
	}

	/**
	 * Função que permite inserir uma palavra na interface gráfica para pesquisar todos os posts com essa palavra
	 * @author Brogueira
	 */

	public void procurarpalavraFace() {
		bfaceprocurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				String procurar=label.getText();
				bda.getFacebook().search(procurar,model);
				setModel(model);
			}
		});
	}

	/**
	 * Função que permite inserir uma palavra na interface gráfica para pesquisar todos os tweets com essa palavra
	 * @author Brogueira
	 */

	public void procurarpalavraTwitter() {
		btwitterprocurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				String procurar=label.getText();
				bda.getTwitter().searchTweet(procurar, model);
				setModel(model);
			}
		});
	}

	/**
	 * Função que permite inserir uma palavra na interface gráfica para pesquisar todos os emails com essa palavra
	 * @author Brogueira
	 */

	public void procurarpalavraMail() {
		bmailprocurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				String procurar=label.getText();
				bda.getMail().procurarpalavra(model,procurar);
				setModel(model);
			}
		});
	}

	/**
	 * Função de leitura do conteúdo dos emails
	 * @author Brogueira
	 */

	public void selectMail() {

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					try {

						Mensagem m = list.getSelectedValue();
						if(m.getTipo().equals("M")) {

							PrintWriter printWriter;
							printWriter = new PrintWriter (new File("file.html"));
							printWriter.println (m.getMailContent());
							printWriter.close ();

							URI uri;
							uri = new URI("file.html");
							uri.normalize();
							Desktop.getDesktop().browse(uri);

						}
					} catch (Exception e) {
						System.out.println("Erro na abertura do Email");
					}
				} 
			}
		});


	}

	/**
	 * Função que organiza por ordem cronológica as Mensagens a apresentar
	 * @param model- lista a organizar
	 * @author Brogueira
	 */

	public void setModel(DefaultListModel<Mensagem> model) {
		ArrayList<Mensagem> m = new ArrayList<>();
		for(int i = 0; i<model.size(); i++) {
			m.add(model.get(i));
		}
		Collections.sort(m, new Comparador());
		this.model.clear();
		for(int i = 0; i<m.size(); i++) {
			this.model.addElement(m.get(i));
		}
	}

}
