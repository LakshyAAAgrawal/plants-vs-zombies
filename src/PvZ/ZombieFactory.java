package PvZ;

public class ZombieFactory {
    public Zombie createZombie(int num, int l_n, double x, double y){
        Zombie zombie = null;
        if(num == 1){
            zombie = new BasicZombie(l_n, x, y);
        }else if(num == 2){
            zombie = new ConeheadZombie(l_n, x, y);
        }else if(num == 3){
            zombie = new BucketheadZombie(l_n, x, y);
        }else{
            zombie = new SpecialZombie(l_n, x, y);
        }
        zombie.createGraphicObject();
        zombie.image.setFitHeight(100);
        zombie.image.setFitWidth(100);
        return zombie;
    }
}
