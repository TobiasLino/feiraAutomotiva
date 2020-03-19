import br.com.fatec.lista2.controller.Controller;
import br.com.fatec.lista2.model.*;

import java.util.Calendar;

public class Testes {
        public static void printa(Calendar n) {
                System.out.println(n.getTime().toString());
        }
        public static void main(String[] args) {
                Client c = new Client();
                c.setName("Tobias");
                c.setCpf("44521581889");

                Phone p = new Phone();
                p.setNumber("12996819353");
                c.setPhone(p);

                Address a = new Address();
                a.setStreet("Avenida José Otávio de Macedo");
                a.setNumber("615");
                a.setCity("Caçapava");
                a.setNeighborhood("Panorama");
                a.setState("SP");
                c.setAddress(a);

                Vehicle v = new Vehicle();
                v.setLicensePlate("abc1234");
                v.setModelVersion("Ômega");
                v.setBrand("Chevrolet");
                v.setPurchasePrice(10000.00);
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, 1993);
                v.setYearOfManufacture(cal);
                c.setVehicle(v);

                Client c2 = new Client();
                c2.setName("Ana Paula");
                c2.setCpf("31018644894");

                Client c3 = new Client();
                c3.setName("Tabata");
                Vehicle v2 = new Vehicle();
                v2.setBrand("Chevrolet");
                v2.setModelVersion("Celta");
                c3.setVehicle(v2);
                Client c4 = new Client();
                c4.setName("Amanda");

                Schedule agenda = new Schedule();
                agenda.add(c);
                agenda.add(c2);
                agenda.add(c3);
                agenda.add(c4);


                Review r = new Review(c);
                Calendar s = Calendar.getInstance();
                s.set(Calendar.DAY_OF_MONTH,19);
                s.set(Calendar.MONTH, 2);
                s.set(Calendar.YEAR, 2020);
                r.setReviewDate(s);

                Review r2 = new Review(c2);
                Calendar s2 = Calendar.getInstance();
                s2.set(Calendar.DAY_OF_MONTH,18);
                s2.set(Calendar.MONTH, 2);
                s2.set(Calendar.YEAR, 2020);
                r2.setReviewDate(s2);

                Review r3 = new Review(c3);
                Calendar s3 = Calendar.getInstance();
                s3.set(Calendar.DAY_OF_MONTH,17);
                s3.set(Calendar.MONTH, 2);
                s3.set(Calendar.YEAR, 2020);
                r3.setReviewDate(s3);

                Revisions rev = new Revisions();
                rev.add(r);
                rev.add(r2);
                rev.add(r3);

                Controller ctrl = new Controller();
                // String str = ctrl.getOption("Ola:");
                ctrl.fileCheck();

                ctrl.sync(agenda, rev);
        }
}
