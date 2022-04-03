import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Profile tanmai = new Profile("Tanmai", 10);
        System.out.println(tanmai.toString());
    }
}

class Profile {

    private String name;
    private int age;
    private Profile[] swipeArr;
    private Profile[] matchArr;

    public Profile() {
        name = "name";
        age = 0;
        swipeArr = new Profile[10];
        matchArr = new Profile[10];
    }

    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
        swipeArr = new Profile[10];
        matchArr = new Profile[10];
    }

    public Profile(String name, int age, Profile[] swipeArr, Profile[] matchArr) {
        this.name = name;
        this.age = age;
        this.swipeArr = swipeArr;
        this.matchArr = matchArr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Profile[] getSwipes() {
        return swipeArr;
    }

    public void setSwipes(Profile[] swipeArr) {
        this.swipeArr = swipeArr;
    }

    // private void setSwipes (Profile profile) {
    // this.setArr[i] = profile;
    // }

    public Profile[] getMatches() {
        return matchArr;
    }
    // private void setMatches(Profile profile){
    // int i = 0;
    // this.matchArr[i] = profile;
    // }

    private void setMatches(Profile[] matchArr) {
        this.matchArr = matchArr;
    }

    public boolean equals(Profile profile) {
        if (name == profile.getName() && age == profile.getAge()) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return ("Name: " + name + "\nAge: " + age + "\nSwiped on: " + Arrays.toString(swipeArr) + "\nMatched with: "
                + Arrays.toString(matchArr));
    }

    public void swipeRight(Profile profile) {
        Profile[] tempSwipeArr = profile.getSwipes();
        int indexToPlace = -1;
        for (int i = 0; i < 10; i++) {
            if (tempSwipeArr[i] == null) {
                indexToPlace = i;
                break;
            }
        }
        if (indexToPlace == -1) {
            System.out.println("This swipe right is not possible.");
            return;
        }
        tempSwipeArr[indexToPlace] = this;
        profile.setSwipes(tempSwipeArr);
        Profile tempProf;
        boolean match = false;
        for (int i = 0; i < 10; i ++) {
            tempProf = swipeArr[i];
            if (tempProf.equals(profile)) {
                match = true;
            }
        }
        if (!match) {
            return;
        }
        Profile[] tempMatchArr = profile.getSwipes();
        indexToPlace = -1;
        for (int i = 0; i < 10; i++) {
            if (tempMatchArr[i] == null) {
                indexToPlace = i;
                break;
            }
        }
        if (indexToPlace == -1) {
            System.out.println("This match is not possible.");
            return;
        }
        tempMatchArr[indexToPlace] = this;
        profile.setMatches(tempMatchArr);

        indexToPlace = -1;
        for (int i = 0; i < 10; i++) {
            if (tempMatchArr[i] == null) {
                indexToPlace = i;
                break;
            }
        }
        if (indexToPlace == -1) {
            System.out.println("This match is not possible.");
            return;
        }
        
    }
    // public void swipeRight(Profile profile){
    // int i = 0;
    // int j = 0;
    // int k = 0;

    // Profile[] tempSwipeArr = profile.getSwipes();

    // while(swipeArr[i]!= null){
    // i++;
    // }

    // profile.setSwipes(tempSwipeArr[i]);

    // for (j=0; j<10; j++){
    // if(swipeArr[i] == tempSwipeArr[j]){
    // matchArr[i] = profile;
    // profile.setMatches(this);
    // }
    // }
    // }

}