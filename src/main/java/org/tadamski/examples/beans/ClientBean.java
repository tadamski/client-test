package org.tadamski.examples.beans;

import org.tadamski.examples.Foo;

import java.util.Hashtable;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Remote(value = Client.class)
public class ClientBean implements Client{

    @Override
    public void invoke() {
        Foo foo = findFoo();
        foo.foo();
    }

    public Foo findFoo() {
        try {
            final Hashtable props = new Hashtable();
            // setup the ejb: namespace URL factory
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            // create the InitialContext
            final Context context = new javax.naming.InitialContext(props);

            //Foo foo = (Foo) context.lookup("ejb:/SimpleEjb-0.0.1-SNAPSHOT//FooBean!org.tadamski.examples.Foo");

            Foo foo = null; //static discovery test - put some your bean here

            return foo;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
