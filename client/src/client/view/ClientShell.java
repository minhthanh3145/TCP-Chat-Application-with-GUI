package client.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import client.model.ClientReceiver;
import client.model.ClientSender;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class ClientShell {
	private Display display;
	private Shell shell;
	private ClientSender clientSendHandler;
	private ClientReceiver clientReceiveHandler;
	private Button btnStartConversation;
	private Composite composite_3;
	private List lstOnlineUsers;
	private Label lblErrorMessage;
	private Text txtChatBox;
	private Label currentCounterParty;
	private List lstChatHistory;
	private Button btnLoadOnlineUsers;
	private String name;
	private ScrolledComposite scrolledComposite;
	private Composite compositeChat;
	private TabItem tbtmInfo;
	private Composite composite_Info;
	private Label label_Avatar;
	private Button btnUpdateAvatar;
	private Label label;
	private Label lblUsername;
	private Text text_username;
	private Label lblPassword;
	private Text text_password;

	public ClientShell() {
	}

	public ClientShell(ClientSender clientSendHandler, ClientReceiver clientReceiveHandler) {
		this.clientSendHandler = clientSendHandler;
		this.clientReceiveHandler = clientReceiveHandler;
		open();
	}

	public void setClientSendHandler(ClientSender clientSendHandler) {
		this.clientSendHandler = clientSendHandler;
		this.clientSendHandler.setClientShell(this);
	}

	public void setReceiveSendHandler(ClientReceiver setReceiveSendHandler) {
		this.clientReceiveHandler = setReceiveSendHandler;
		this.clientReceiveHandler.setClientShell(this);
	}

	public void setShellTitle(String title) {
		name = title;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ClientShell window = new ClientShell();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContent();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContent() {
		shell = new Shell();
		shell.setSize(527, 337);
		shell.setText(name);
		createComposites();
		registerControlListeners();
	}

	private void createComposites() {
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(0, 0, 511, 298);
		
		tbtmInfo = new TabItem(tabFolder, SWT.NONE);
		tbtmInfo.setText("Personal Info");
		
		composite_Info = new Composite(tabFolder, SWT.NONE);
		composite_Info.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tbtmInfo.setControl(composite_Info);
		
		label_Avatar = new Label(composite_Info, SWT.NONE);
		label_Avatar.setImage(SWTResourceManager.getImage("D:\\ZD_T1_Y4\\ZD_CS494_IP\\TCP-Chat-Application-with-GUI\\client\\empty_avatar.jpeg"));
		label_Avatar.setBounds(24, 24, 128, 128);
		
		btnUpdateAvatar = new Button(composite_Info, SWT.NONE);
		btnUpdateAvatar.setBounds(40, 170, 96, 25);
		btnUpdateAvatar.setText("Update Avatar...");
		
		label = new Label(composite_Info, SWT.NONE);
		label.setAlignment(SWT.CENTER);
		label.setText("<Nickname>");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		label.setBounds(190, 10, 303, 42);
		
		lblUsername = new Label(composite_Info, SWT.NONE);
		lblUsername.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblUsername.setAlignment(SWT.CENTER);
		lblUsername.setBounds(190, 78, 71, 25);
		lblUsername.setText("Username");
		
		text_username = new Text(composite_Info, SWT.BORDER);
		text_username.setEditable(false);
		text_username.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		text_username.setBounds(267, 75, 226, 25);
		
		lblPassword = new Label(composite_Info, SWT.NONE);
		lblPassword.setText("Password");
		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblPassword.setAlignment(SWT.CENTER);
		lblPassword.setBounds(190, 139, 71, 25);
		
		text_password = new Text(composite_Info, SWT.BORDER);
		text_password.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		text_password.setEditable(false);
		text_password.setBounds(267, 136, 226, 25);
		
		TabItem tbtmChat = new TabItem(tabFolder, SWT.NONE);
		tbtmChat.setText("Chat");
		
		compositeChat = new Composite(tabFolder, SWT.NONE);
		compositeChat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tbtmChat.setControl(compositeChat);
		
				Composite composite_2 = new Composite(compositeChat, SWT.NONE);
				composite_2.setLocation(160, 0);
				composite_2.setSize(343, 46);
				
						btnStartConversation = new Button(composite_2, SWT.NONE);
						btnStartConversation.setLocation(0, 0);
						btnStartConversation.setSize(109, 25);
						btnStartConversation.setText("Start Conversation");
						
								lblErrorMessage = new Label(composite_2, SWT.NONE);
								lblErrorMessage.setBounds(0, 31, 343, 15);
								lblErrorMessage.setText("");
								
										currentCounterParty = new Label(composite_2, SWT.NONE);
										currentCounterParty.setBounds(201, 5, 142, 20);
										currentCounterParty.setText("");
										
												composite_3 = new Composite(compositeChat, SWT.NONE);
												composite_3.setLocation(160, 52);
												composite_3.setSize(343, 218);
												
														txtChatBox = new Text(composite_3, SWT.BORDER);
														txtChatBox.setBounds(0, 169, 343, 49);
														
														scrolledComposite = new ScrolledComposite(composite_3, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
														scrolledComposite.setBounds(0, 0, 343, 163);
														scrolledComposite.setExpandHorizontal(true);
														scrolledComposite.setExpandVertical(true);
														
														lstChatHistory = new List(scrolledComposite, SWT.BORDER);
														scrolledComposite.setContent(lstChatHistory);
														scrolledComposite.setAlwaysShowScrollBars(true);
														lstOnlineUsers = new List(compositeChat, SWT.BORDER);
														lstOnlineUsers.setLocation(0, 49);
														lstOnlineUsers.setSize(152, 221);
														
																btnLoadOnlineUsers = new Button(compositeChat, SWT.NONE);
																btnLoadOnlineUsers.setLocation(10, 10);
																btnLoadOnlineUsers.setSize(134, 25);
																btnLoadOnlineUsers.setText("Load online users");
	}

	private void registerControlListeners() {

		btnStartConversation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (lstOnlineUsers.getSelection().length == 1) {
					currentCounterParty.setText(lstOnlineUsers.getSelection()[0]);
				} else {
					lblErrorMessage.setText("Select an online user to start a conversation");
				}
			}
		});

		txtChatBox.addListener(SWT.DefaultSelection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				if (!currentCounterParty.getText().equals("")) {
					clientSendHandler.sendMessage("Unicast", currentCounterParty.getText(), txtChatBox.getText());
				}
			}
		});

		btnLoadOnlineUsers.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clientSendHandler.sendMessage("Online User Lists");
			}
		});
	}

	public void updateOnlineUsersList(String... users) {
		lstOnlineUsers.setItems(users);
	}

	public void updateChatHistory(String newMessage) {
		lstChatHistory.add(newMessage);
	}

	public Display getDisplay() {
		return display;
	}
}
