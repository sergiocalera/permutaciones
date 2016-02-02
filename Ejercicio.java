/*

Nombre: Calera Fortis Sergio Asurin

*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Permutaciones per = new Permutaciones();
        Scanner sc = new Scanner(System.in);
        switch (args.length){
            case 2 :
                per.setElementos(Integer.valueOf(args[0]));
                per.setSalida(Integer.valueOf(args[1]));
                break;
            case 1 :
                System.out.print("Introduce el numero de combinaciones ( k ): ");
                per.setElementos(Integer.valueOf(args[0]));
                per.setSalida(sc.nextInt());
                break;
            case 0:
                System.out.print("Introduce el numero a permutar y el numero de de combinaciones ( n k ): ");
                String datos[] = sc.nextLine().split("\\s+");
                
                per.setElementos(Integer.valueOf(datos[0]));
                per.setSalida(Integer.valueOf(datos[1]));
        }
        if(per.getNumeroDeElementos() == 1){
            System.out.println("{1}");
        }else{
            for(Salida salida : per.getSalida()){
                System.out.println(salida);
            }
        }
    }
    
}

class Permutaciones{
    
    private List<String> elementos;
    private int k;
    private List<Salida> combinaciones;
    private long contador;
    
    public Permutaciones(){
        this.combinaciones = new ArrayList<>();
        this.contador = 0;
    }
    
    public void setElementos(int n){
    this.elementos = datos(n);
    }
    
    public void setSalida(int k){
        this.k = k;
    }
    
    public int getNumeroDeElementos(){
        return this.elementos.size();
    }
    
    public List<Salida>getSalida(){
        permutar(new ArrayList<>(), elementos, elementos.size(), true);
        return combinaciones;
    }

    private List<String> datos(int n){
        List<String> aux = new ArrayList<>();
        for(int k=1; k<=n; k++){
            aux.add(String.valueOf(k));
        }
        return aux;
    }
    
    private boolean permutar(List<String> extras, List<String> elementos, int j, boolean bandera){
        boolean continuar = true;
        if(continuar){
            if(elementos.size() != 2){
                for(int i = 0; i < j; i++){
                    List<String> aux2 = clonar(elementos);
                    String temp = aux2.remove(i);
                    aux2.add(0, temp);
                    List<String> auxExtras = clonar(extras);
                    auxExtras.add(aux2.remove(0));
                    extras = clonar(auxExtras);
                    continuar = permutar(extras, aux2, j-1, true);
                    extras = auxExtras.subList(0, auxExtras.size()-1);
                }
            }
            else{
                if(contador >= k){
                    return false;
                }
                if(bandera){
                    extras.addAll(elementos);
                    combinaciones.add(new Salida(extras, ++contador));
                    continuar = permutar(extras.subList(0, extras.size()-2), extras.subList(extras.size()-2, extras.size()), k, false);
                }
                else{
                    List<String> aux2 = clonar(extras);
                    String temp = elementos.remove(elementos.size()-2);
                    elementos.add(temp);
                    aux2.addAll(elementos);
                    combinaciones.add(new Salida(aux2, ++contador));
                    extras = aux2;
                    aux2.clear();
                }
            }
        }
        return false;
    }
    
    private List<String> clonar(List<String> elementos){
        List<String> aux = new ArrayList<>();
        for(String elemento : elementos){
            aux.add(elemento);
        }
        return aux;
    }
}

class Salida{
    
    private List<String> elementos;
    private long posicion;
    
    public Salida(List<String> elementos, long posicion){
        this.elementos = new ArrayList<>();
        setElementos(elementos);
        this.posicion = posicion;
    }
    
    private void setElementos(List<String> elementos){
        elementos.stream().forEach((elemento) -> {
            this.elementos.add(elemento);
        });
    }
    
    @Override
    public String toString(){
        String aux = "{";
        for(int n = 0; n < elementos.size(); n++){
            if(n != (elementos.size()-1)){
                aux += elementos.get(n) + ", ";
            }
            else{
                aux += elementos.get(n) + "}" ;
            }            
        }
        
        return this.posicion + " .- " + aux;
    }
}
