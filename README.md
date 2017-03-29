# Film-O-Matic
An automatic film development machine built on Raspberry Pi. Keeping analog digital.

Things you will need:
* Raspberry Pi B+ (2,3,0)
* 3D Printer with black ABS filament
* Acrylic and acrylic glue
* 12V heating element
* 8 Relays
* 7 Solenoids
* A bunch of 1/4" ID tube
* 3 Impeller pumps
* Waterproof DS18B20 temp sensor

## Using the Remote Agent for Integration Testing

### Step 1 - Configure and Deploy the Remote Agent

First, we need to provide some SSH connection information to your Pi which you want
to use for integration testing. To do this, place a configuration file in your home
directory called `.pis.conf`, and follow this template:

```hocon
servers = [
  {
    name = "pi"
    user = "pi"
    host = "[ip address of pi]"
    sshDir = "[full path to your ~/.ssh/ directory]"
    sshKeyFile = "[the ssh key file to use, e.g. id_rsa]"
    passphrase = "[the passphrase to the key]"
  }
]
```

Once the configuration file is in place, use the sbt console to start the remote agent
using `deploy-ssh pi`:

```bash
localhost:~ $ sbt
...
> deploy-ssh pi
...
[success] Total time: 49 s, completed Mar 28, 2017 8:03:15 PM
```

The agent will continue running in the background after you `quit` the console. You can
log on and view log output from the agent in real-time by logging on to your Pi over
SSH in a terminal and tailing the output file of the remote agent:

```bash
localhost:~ $ ssh pi@[ip address of pi]
pi@raspberrypi:~ $ tail -f filmomatic/remote/out
```

### Step 2 - Configure Your Test

Regardless of what IDE you are using, you'll need to end up running the tests in the
integration module with the following additional arguments passed to the JVM:

```bash
java -javaagent:${HOME}/.ivy2/cache/org.aspectj/aspectjweaver/jars/aspectjweaver-1.8.7.jar \
  -Dakka.cluster.seed-nodes.0="akka.tcp://pi4j-remoting@[ip address of pi]:2552" \
  -Dakka.remote.netty.tcp.hostname="[ip address of your workstation]" \
  -Dpi4j.client.mode=remote-client [...the rest]
```

For me, in IntelliJ, setting this up was a matter of creating a new run configuration named
"Integration" with the following settings:

1. Set "Test kind" to "All in package"
2. Set "Test Package" to `com.bradbrok.filmomatic`
3. Set "Search for tests" to "In single module"
4. Set "VM Parameters" to `-javaagent:/Users/srial/.ivy2/cache/org.aspectj/aspectjweaver/jars/aspectjweaver-1.8.7.jar -Dakka.cluster.seed-nodes.0="akka.tcp://pi4j-remoting@192.168.1.21:2552" -Dakka.remote.netty.tcp.hostname="192.168.1.11" -Dpi4j.client.mode=remote-client`
5. Set "Use classpath and SDK of module" to "integration"

Once the above is configured, hit the apply button.

### Step 3 - Enjoy Local Development

Hitting the run button should run the `SmokeSpec` successfully, and hitting the debug button
should allow you to set breakpoints and do all of the normal local development things.