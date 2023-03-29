package net.filonova.project.notes.daoImpl.endpoint;

import net.filonova.project.notes.endpoint.AccountsEndpoint;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountsEndpoint.class)
public class TestAccountsEndpoint {

}
