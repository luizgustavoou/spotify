package imd.ufrn.br.spotify.helpers;


public enum UserType {
    FREE (0){
        @Override
        public boolean isFree() {
            return true;
        }
    },
    VIP (1){
        @Override
        public boolean isVip() {
            return true;
        }
    };

    private int type;

    public boolean isFree() {return false;}

    public boolean isVip() {return false;}


    UserType (int type) {
        this.type = type;
    }

    public static void main(String[] args) {
        UserType userType = UserType.FREE;

        System.out.println(userType.ordinal());
    }

}