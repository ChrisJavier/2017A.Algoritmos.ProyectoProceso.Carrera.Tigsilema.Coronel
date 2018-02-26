/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

public class ArbolbinarioBalanceado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArbolAVL tree = new ArbolAVL();
 
        /* Constructing tree given in the above figure */
        tree.insertarProceso("3",9,1);
        tree.insertarProceso("1",5,4);
        tree.insertarProceso( "2",5,4);
        tree.insertarProceso("5", 10,4);
        tree.insertarProceso("7", 0,9);
        tree.insertarProceso("9",6,1);
        tree.insertarProceso("8",11,6);
        tree.insertarProceso("18",-1,1);
        tree.insertarProceso("1", 1,3);
       tree.insertarProceso("13",2,2);
       
       tree.insertarProceso("78",15,12);
       tree.insertarProceso("73",13,72);
       tree.insertarProceso("12",16,19);
 
        /* The constructed AVL Tree would be
           9
          /  \
         1    10
        /  \    \
        0    5    11
        /    /  \
        -1   2    6
         */
        System.out.println("Preorder traversal of "+
                            "constructed tree is : ");
        tree.imprimirInorder();
 
       // tree.eliminar(10);
 
        /* The AVL Tree after deletion of 10
           1
          /  \
         0    9
        /     / \
        -1    5   11
        /  \
        2    6
         */
        /*System.out.println("");
        System.out.println("Preorder traversal after "+
                           "deletion of 10 :");*/
       // tree.ImprimiPreOrder();
       /* Nodo temp = tree.masPrioritario(); 
        System.out.println("nodo mas prioritario");
        System.out.println(temp.getKey());
        
        tree.imprimiPreOrder();
        System.out.println("nodos  mas prioritario");
        tree.imprimirMasPrioritario();
        
        */
       
      
       Nodo prior = tree.getMasPrioritario(tree.getRaiz()); 
       System.out.println("nodo mas prioritario ");
       System.out.println(prior.getKey()); 
       
       System.out.println("tranversal ");
       
       //tree.imprimiPreOrder();
        System.out.println("\n");
       tree.imprimirMasPrioritario();
       System.out.println("\n");
       tree.imprimirInorder();
       System.out.println("nodo prioritario2 "); 
       
       tree.limpiarTemp();
       tree.actualizarFlag();
       Nodo prior2= tree.getMasPrioritario(tree.getRaiz());
       System.out.println("mas prior1 "+prior2.getKey());
       tree.imprimirMasPrioritario();
      //if(prior2 == )
       
    }
    
}
