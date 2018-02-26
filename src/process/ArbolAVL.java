package process;


import process.Nodo;
import process.Process;

public class ArbolAVL  {
      private Nodo raiz;
      private Nodo temp=null; 
    private Object txtArea;
      //boolean mayor=false;
    // metodo para obtener la altura del arbol
   private int altura(Nodo N)
    {
        if (N == null)
             return 0;
         return N.getAltura_();
    }
    
    public void limpiarTemp(){
       this.temp = null; 
    }
    
    
    private int maximo(int a, int b)
    {
        return (a > b) ? a : b;
    }
 
    //metodo para realizar la rotación a la derecha 
   private Nodo rotacionDerecha(Nodo y)
    {
        Nodo x = y.izq;
        Nodo T2 = x.der;
 
        //realizar la rotacion 
        x.der = y;
        y.izq = T2;
 
        // actualizar alturas
        y.setAltura_(maximo(altura(y.izq), altura(y.der)) + 1);
        x.setAltura_(maximo(altura(x.izq), altura(x.der)) + 1);
 
        // Return new root
        return x;
    }
 
    //rotación izquierda.
    private Nodo rotacionIzquierda(Nodo x)
    {
        Nodo y = x.der;
        Nodo T2 = y.izq;
 
       
        y.izq = x;
        x.der = T2;
 
        //actualizar la actura de los nodos 
        x.setAltura_(maximo(altura(x.izq), altura(x.der)) + 1);
        y.setAltura_(maximo(altura(y.izq), altura(y.der)) + 1);
 
        
        return y;
    }
 
    
    private int getBalance(Nodo N)
    {
        if (N == null)
            return 0;
        return altura(N.izq) - altura(N.der);
    }
    public void insertarProceso( String pid, int key, float time){
        this.raiz = insert(this.raiz, pid,key, time);
    
    
    }
    private Nodo insert(Nodo nodo,String pid, int key, float time)
    {
       /*insertar cambiar de key a que se inserte por proceso.getprioridad
        osea key < nodo.getkey se cambie a 
        proceso.getprioridad() < nodo.getproceso.getprioridad 
        */
        if (nodo == null)
            
            //cambiar return (new Nodo(proceso))
            return (new Nodo(pid, key, time));
 
        if (key < nodo.getKey())
            nodo.izq = insert(nodo.izq,pid, key,time);
        else if (key >= nodo.getKey())
            nodo.der = insert(nodo.der,pid, key, time);
       
 
        /* 2. actualiar la altura del nodo ancestro  */
        nodo.setAltura_(1 + maximo(altura(nodo.izq),altura(nodo.der)));
 
        /* 3. obtener el factor de valance de este nodo ancestro 
        para saber si este nodo esta desbalanceado */
        int balance = getBalance(nodo);
 
        // si esta desbalanceado tenemos 4 casos 
        // caso izquierda izquierda, rotacion derecha 
        if (balance > 1 && key < nodo.izq.getKey())
            return rotacionDerecha(nodo);
 
        // caso derecha derecha, rotacion a la izquierda 
        if (balance < -1 && key > nodo.der.getKey())
            return rotacionIzquierda(nodo);
 
        //case izquierda derecha, doble rotacion izquierda, derecha 
        if (balance > 1 && key > nodo.izq.getKey())
        {
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }
 
        // Derecha izquierda, doble rotacion derecha, izquierda 
        if (balance < -1 && key < nodo.der.getKey())
        {
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }
 
        
        return nodo;
    }
 
    /* metodo para buscar el nodo minimo de un arbol. */
   private Nodo nodoMinimoValor(Nodo node)
    {
        Nodo actual = node;
 
      
        while (actual.izq != null)
           actual = actual.izq;
 
        return actual;
    }
   
   
   
 
    public void eliminar(int key){
        this.raiz = deleteNode(this.raiz, key); 
        
    }    
    private Nodo deleteNode(Nodo raiz, int key)
    {
        // paso 1: borrado estandar 
        if (raiz == null)
            return raiz;
 
       
        if (key < raiz.getKey())
            raiz.izq = deleteNode(raiz.izq, key);
         else if (key > raiz.getKey())
            raiz.der = deleteNode(raiz.der, key);
 
        // borrar el nodo que se desea 
        else
        {
 
            // nodo con un solo hijo 
            if ((raiz.izq == null) || (raiz.der == null))
            {
                Nodo temp = null;
                if (raiz.izq==null)
                    temp = raiz.der;
                else
                    temp = raiz.izq;
 
                // caso sin hijos
                if (temp == null)
                {
                    temp = raiz;
                    raiz = null;
                }
                else   // caso de un solo hijo 
                    raiz = temp; //copiamos el contenido del hijo al padre
            }
            else
            {
 
                // nodo con dos hijos, obtener el nodo minimo a partir del actual 
                Nodo temp = nodoMinimoValor(raiz.der);
                             
                raiz.setKey(temp.getKey());
 
                // borrar el nodo minimo
                raiz.der = deleteNode(raiz.der, temp.getKey());
            }
        }
 
        // si hay un solo nodo 
        if (raiz == null)
            return raiz;
 
        // paso 2: actualizar las alturas del nodo actual 
        raiz.setAltura_(maximo(altura(raiz.izq), altura(raiz.der)) + 1);
 
        // paso 3: obtener el factor de balance, para saber si esta desbalanceado 
        int balance = getBalance(raiz);
 
        // si el nodo actual esta desbalanceado ,  si tiene 4 casos
        // rotacion a la derecha 
        if (balance > 1 && getBalance(raiz.izq) >= 0)
            return rotacionDerecha(raiz);
 
        // doble rotacion izquierda 
        if (balance > 1 && getBalance(raiz.izq) < 0)
        {
            raiz.izq = rotacionIzquierda(raiz.izq);
            return rotacionDerecha(raiz);
        }
 
        // rotacion  a la izquierda 
        if (balance < -1 && getBalance(raiz.der) <= 0)
            return rotacionIzquierda(raiz);
 
        // doble rotacion derecha e izquierda 
        if (balance < -1 && getBalance(raiz.der) > 0)
        {
            raiz.der = rotacionDerecha(raiz.der);
            return rotacionIzquierda(raiz);
        }
 
        return raiz;
    }
 
    public void imprimiPreOrder(){
      preOrder(this.raiz); 
    }
    private void preOrder(Nodo nodo)
    {
        if (nodo != null)
        {
            System.out.print(nodo.getKey() + " ");
            preOrder(nodo.izq);
            preOrder(nodo.der);
        }
        
        
        
       
}
    
  public void imprimirMasPrioritario(){
      imprimirPrioritario(this.raiz);
  }
  
  private void imprimirPrioritario(Nodo root){
     if(root!=null){
        imprimirPrioritario(root.der);
        System.out.print(root.getKey() + " ");
        imprimirPrioritario(root.izq);
     }
  
  }
    boolean flag=false; 
    public void actualizarFlag(){
      this.flag = false; 
    
    }
    
    
    public Nodo masPrioritario(){
      Nodo temp1= null; 
      if(raiz.der ==null && raiz.izq==null){

           temp1  = raiz; 
            raiz= null;
            return temp1;
      
      }else if(raiz.izq!=null && raiz.der==null){
      temp1 = raiz;
      raiz = temp1 .izq;
      raiz.izq = null;  
     return temp1; 
    }else if(raiz.izq==null && raiz.der!=null){
      temp1 = raiz.der; 
      raiz.der = null; 
      return temp1; 

}else if(raiz.der.izq==null && raiz.der.der ==null && raiz.izq.izq==null && raiz.izq.der==null){
    
     temp1= raiz.der; 
      raiz.der = null; 
     return temp1;
}else{

  return getMasPrioritario(this.raiz); 
}   
    
    
    }
    
    
    Nodo getMasPrioritario(Nodo root){
        
        
        
        if(root.der.der!=null){
             //recorrer recurisvamente el arbol hasta un nodo anterior al mas prioritario 
             root = getMasPrioritario(root.der);
        }
        if(flag==false){
           
            // caso en que el nodo mas prioritario no tenga hijos 
        if(root.der.der==null && root.der.izq == null /*&& mayor == false*/){
             temp = root.der;   
            
             root.der = null;
            
             flag=true; 
        // caso en que el nodo mas prioritario  tenga un hijo izquierdo  
        }else if(root.der.izq!=null /*&&  mayor==false*/){
               temp = root.der;
               
               root.der = temp.izq; 
               root.der.izq = null; 
              
               flag=true; 
        }
        //actaulizamos la altura de los nodos 
        root.setAltura_(maximo(altura(root.izq),altura(root.der)) + 1);
        // realizamos una rotacion a la derecha, en el caso que este desbalanceado 
        int balance = getBalance(root);
        if (balance < -1 && getBalance(root.der) <= 0)
            return rotacionDerecha(root);
        }
        return temp; 
    
        
    }

    public Nodo getRaiz() {
        return raiz;
    }
   
    public void imprimirInorder(){
            Inorder(this.raiz);
    
    }
    
    
      @SuppressWarnings("empty-statement")
    public void Inorder(Nodo raiz){
        
        if(raiz!=null){
          
          Inorder(raiz.izq); 
          System.out.print(raiz.getKey() + " ");
          Inorder(raiz.der);
        
          
        }
        
    }
    
}
