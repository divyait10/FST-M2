package activities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;


@Provider("UserProvider")
@PactFolder("target/pacts")
public class PactProviderTest {
	
	@BeforeEach
	void before(PactVerificationContext context) {
	    context.setTarget(new HttpTestTarget("localhost", 8282));
	}
	
	 @TestTemplate
     @ExtendWith(PactVerificationInvocationContextProvider.class)
     void pactTestTemplate(PactVerificationContext context) {
          context.verifyInteraction();
     }
	 
	 @State("A request to create a user")
	 public void sampleState() {}
	 
	 

}