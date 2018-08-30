package com.ritds;

public class Observation implements Comparable<Observation> {
    private long timestamp;
    private String data;

    public Observation(long timestamp, String data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    @Override
    public int compareTo(Observation o) {
        if (this.timestamp > o.timestamp) return 1;
        else if (this.timestamp < o.timestamp) return -1;
        else return 0;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Observation or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Observation)) {
            return false;
        }

        // typecast o to Observation so that we can compare data members
        Observation c = (Observation) o;

        // Compare the data members and return accordingly
        return this.timestamp == c.timestamp;
    }

}
