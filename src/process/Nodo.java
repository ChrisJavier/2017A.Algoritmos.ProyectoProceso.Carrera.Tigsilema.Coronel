package process;

public class Nodo {
private int key, altura_; 
String pid;
float time;

      
 Nodo izq,der;
      
     
    public Nodo(String l,int d, float r) {
        key = d;
        pid= l;
        time= r;
        altura_ = 1;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
    public String getPID() {
        return pid;
    }

    public void setPID(String pid) {
        this.pid = pid;
    }
    
    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
    public int getAltura_() {
        return altura_;
    }

    public void setAltura_(int altura_) {
        this.altura_ = altura_;
    }

    
    
   
}

