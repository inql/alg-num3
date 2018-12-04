package ug.pprotocols;

public enum Type {

    GAUSS{
        @Override
        public String toString(){
            return "GAUSS";
        }
    },
    GAUSS_SPARSE{
        @Override
        public String toString(){
            return "GAUSS_SPARSE";
        }
    },
    JACOBIAN{
        @Override
        public String toString(){
            return "JACOBIAN";
        }
    },
    GAUSS_SEIDEL{
        @Override
        public String toString(){
            return "GAUSS_SEIDEL";
        }
    }


}
