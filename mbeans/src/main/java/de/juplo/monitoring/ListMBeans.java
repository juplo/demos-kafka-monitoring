package de.juplo.monitoring;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Iterator;
import java.util.Set;


public class ListMBeans
{
    public static void main(String[] args)
    {
        String url = args.length > 0 ? args[0] : "service:jmx:rmi:///jndi/rmi://:7001/jmxrmi";
        System.out.println("-------------------------------------------------");
        try {
            JMXServiceURL jmxServiceURL = new JMXServiceURL(url);
            JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, null);

            MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();

            Set result = connection.queryNames(new ObjectName("*:*"), null);
            Iterator it = result.iterator();

            while (it.hasNext())
            {
                ObjectName objectName = (ObjectName) it.next();

                try
                {
                    System.out.println("--> " + objectName.getCanonicalName());
                    MBeanAttributeInfo[] attributes = connection.getMBeanInfo(objectName).getAttributes();

                    for (int i = 0; i < attributes.length; i++)
                    {
                        System.out.println("         Attribute: " + attributes[i].getName() + "   of Type : " + attributes[i].getType());
                    }

                    MBeanOperationInfo[] operations = connection.getMBeanInfo(objectName).getOperations();

                    for (int i = 0; i < operations.length; i++)
                    {
                        System.out.print("         Operation: " + operations[i].getReturnType() + "  " + operations[i].getName() + "(");
                        for (int j = 0; j < operations[i].getSignature().length;j++)
                        {
                          System.out.print(operations[i].getSignature()[j].getName() +
                            ":" +
                            operations[i].getSignature()[j].getType() +
                            "  ");
                        }
                        System.out.println(")");
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            jmxConnector.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("-------------------------------------------------");
    }
}
