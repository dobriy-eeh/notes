import org.slf4j.LoggerFactory
import java.lang.management.ManagementFactory
import javax.management.remote.JMXServiceURL
import javax.management.remote.jmxmp.JMXMPConnectorServer

private val log = LoggerFactory.getLogger(JmxMpServer::class.java)

class JmxMpServer(host: String, port: Int) {
    private val url = JMXServiceURL(null, host, port)
    private val server: JMXMPConnectorServer

    init {
        val mbs = ManagementFactory.getPlatformMBeanServer()
        val env = HashMap<String, String>()
        env["jmx.remote.x.server.reuse.address"] = true.toString()
        env["jmx.remote.server.address.wildcard"] = false.toString()
        server = JMXMPConnectorServer(url, null, mbs)
    }

    fun start() {
        server.start()
        log.info("Jmxmp started on $url")
    }

    fun stop() {
        server.stop()
    }
}
