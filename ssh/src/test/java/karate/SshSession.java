package karate;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SshSession {

    private final Channel channel;
    private final Session session;
    private final PrintStream terminal;

    public SshSession(Map<String, Object> map) {
        try {
            String user = (String) map.get("user");
            String host = (String) map.get("host");
            String privateKey = (String) map.get("privateKey");
            Integer port = (Integer) map.get("port");
            if (port == null) {
                port = 22;
            }
            JSch jsch = new JSch();
            jsch.addIdentity(privateKey);
            session = jsch.getSession(user, host, port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("shell");
            OutputStream channelInput = channel.getOutputStream();
            terminal = new PrintStream(channelInput, true);
            channel.setOutputStream(System.out, true);
            ((ChannelShell) channel).setPty(true);
            channel.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void input(String line) {
        terminal.println(line);
    }

    public void close() {
        try {
            do {
                TimeUnit.SECONDS.sleep(1);
            } while (!channel.isEOF());
            session.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
