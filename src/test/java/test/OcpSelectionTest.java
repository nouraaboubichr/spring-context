package test;

import config.AppConfig;
import metier.IMetier;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class OcpSelectionTest {

    @After
    public void nettoyer() {
        System.clearProperty("dao.target");
    }

    private double calculAvecCible(String cible) {
        System.setProperty("dao.target", cible);
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        try {
            return context.getBean(IMetier.class).calcul();
        } finally {
            context.close();
        }
    }

    @Test
    public void testDao() {
        assertEquals(200.0, calculAvecCible("dao"), 0.001);
    }

    @Test
    public void testDaoApi() {
        assertEquals(440.0, calculAvecCible("daoApi"), 0.001);
    }
}