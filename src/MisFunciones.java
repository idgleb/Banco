import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/*
        String[] opc = {"agregar_producto", "buscar_producto", "eliminar_producto"};
        Runnable[] ac = new Runnable[]{
                () -> repositor.agregar_producto(stock),
                () -> repositor.buscar_producto(stock),
                () -> repositor.eliminar_producto(stock),
        };
        MisFunciones.interfaz(ac, opc, stock::toString);
*/

public class MisFunciones {

    public static Object eligirObjDeLista(List list) {

        if (list.isEmpty()) return null;

        return JOptionPane.showInputDialog(null,
                "elige", "Ursol",
                JOptionPane.QUESTION_MESSAGE, null, list.toArray(), 0);

    }

    public static int eligirEn(String[][] matriz) {

        String[] listaElementosEnMatriz = new String[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            listaElementosEnMatriz[i] = Arrays.toString(matriz[i]);
        }

        String elementoEligido = (String) JOptionPane.showInputDialog(null,
                "Elege...", "Ursol",
                JOptionPane.QUESTION_MESSAGE, null, listaElementosEnMatriz, null);

        if (elementoEligido != null) {
            int item = Arrays.asList(listaElementosEnMatriz).indexOf(elementoEligido);
            return item;
        }

        return -1;

    }

    public static boolean isNumeroDe_1_10000000(String str) {
        if (str.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) < '0' || str.charAt(i) > '9') return false;
            }
            int num = Integer.parseInt(str);
            if (num < 1 || num > 1000000000) return false;
        }
        return true;
    }

    public static boolean isDoubleMasCero(String str) {
        int cantComas = 0;
        if (str.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) < '0' || str.charAt(i) > '9'){
                    if (str.charAt(i) != '.'){
                        return false;
                    }else {
                        cantComas++;
                    }
                    if (cantComas > 1) return false;
                }
            }
            double num = Double.parseDouble(str);
            if (num < 0) return false;
        }
        return true;
    }

    public static int pedirNumeroMasCero(String msg) {
        String str;
        do {
            str = JOptionPane.showInputDialog(null, msg);
            if (str == null) return -1;
        } while (str.isEmpty() || !isNumeroDe_1_10000000(str));
        return Integer.parseInt(str);
    }

    public static double pedirDoubleMasCero(String msg) {
        String str;
        do {
            str = JOptionPane.showInputDialog(null, msg);
            if (str == null) return -1;
        } while (str.isEmpty() || !isDoubleMasCero(str));
        return Double.parseDouble(str);
    }

    public static String pedirStrNoVacio(String msg) {
        String str;
        do {
            str = JOptionPane.showInputDialog(null, msg);
            if (str == null) return null;
        } while (str.trim().isEmpty());
        return str;
    }

    public static LocalDate pedirFechaMasDeHoy(String msg, String msgError) {
        LocalDate fechaEntr;
        do {
            fechaEntr = LocalDate.parse(JOptionPane.showInputDialog(null, msg));
            if (fechaEntr.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(null, msgError);
            }
        } while (fechaEntr.isBefore(LocalDate.now()));
        return fechaEntr;
    }

    public static LocalDate pedirFechaMasDeOtraFecha(LocalDate otraFecha, String msg, String msgError) {
        LocalDate fecha;
        do {
            fecha = LocalDate.parse(JOptionPane.showInputDialog(null, msg));
            if (fecha.isBefore(otraFecha)) {
                JOptionPane.showMessageDialog(null, msgError);
            }
        } while (fecha.isBefore(otraFecha));
        return fecha;
    }

    public static void interfaz(Runnable[] ac, String[] opc, Supplier<String> report) {
        List<String> opcList = new ArrayList<>(Arrays.asList(opc));
        opcList.addFirst("Salir");
        int select = 0;
        do {
            select = JOptionPane.showOptionDialog(
                    null,
                    report.get(),
                    "Ursol",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcList.toArray(),
                    opcList.getFirst()
            );

            if (select > 0) ac[select - 1].run();

        } while (select > 0);
    }

}
