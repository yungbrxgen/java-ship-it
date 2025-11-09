import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private List<T> parcels = new ArrayList<>();
    private int maxWeight;
    private int currentWeight = 0;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean addParcel(T parcel) {
        if(currentWeight + parcel.getWeight() <= maxWeight) {
            parcels.add(parcel);
            currentWeight +=parcel.getWeight();
            return true;
        } else {
            System.out.println("Превышен максимальный вес коробки, посылка не добавлена.");
            return false;
        }
    }

    public List<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }
}
