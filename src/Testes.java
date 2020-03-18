import br.com.fatec.lista2.model.*;

import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

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

                agenda.remove(c);

                Review r = new Review();
                Calendar s = Calendar.getInstance();
                s.set(Calendar.DAY_OF_MONTH,19);
                s.set(Calendar.MONTH, 04);
                s.set(Calendar.YEAR, 2020);

                r.setReviewDate(s);
                r.setClient(c);

                Revisions rev = new Revisions();
                rev.add(r);
        }
}
