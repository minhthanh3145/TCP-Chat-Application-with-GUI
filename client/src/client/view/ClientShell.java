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

		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setBounds(158, 10, 353, 64);

		btnStartConversation = new Button(composite_2, SWT.NONE);
		btnStartConversation.setLocation(0, 0);
		btnStartConversation.setSize(109, 25);
		btnStartConversation.setText("Start Conversation");

		lblErrorMessage = new Label(composite_2, SWT.NONE);
		lblErrorMessage.setBounds(0, 31, 343, 15);
		lblErrorMessage.setText("");

		currentCounterParty = new Label(composite_2, SWT.NONE);
		currentCounterParty.setBounds(115, 5, 142, 20);
		currentCounterParty.setText("");

		composite_3 = new Composite(shell, SWT.NONE);
		composite_3.setBounds(158, 80, 353, 218);

		txtChatBox = new Text(composite_3, SWT.BORDER);
		txtChatBox.setBounds(0, 169, 353, 49);
		
		scrolledComposite = new ScrolledComposite(composite_3, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 0, 353, 163);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		lstChatHistory = new List(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(lstChatHistory);
		scrolledComposite.setAlwaysShowScrollBars(true);
		lstOnlineUsers = new List(shell, SWT.BORDER);
		lstOnlineUsers.setBounds(0, 38, 152, 260);

		btnLoadOnlineUsers = new Button(shell, SWT.NONE);
		btnLoadOnlineUsers.setText("Load online users");
		btnLoadOnlineUsers.setBounds(25, 7, 109, 25);
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
