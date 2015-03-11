package test;

public class Son
{
    /**
     * 内部类继承Father类
     */
    class Father_1 extends Father{
        public int strong(){
            return super.strong() + 1;
        }
    }

    class Mother_1 extends  Mother{
        public int kind(){
            return super.kind() - 2;
        }
    }

    public int getStrong(){
        return new Father_1().strong();
    }

    public int getKind(){
        return new Mother_1().kind();
    }
}
