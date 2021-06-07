package models;

public class Solution {
    public int[] affectations = null;
    public int[] interfacesHours = null;
    public double cost = 0;
    public int matchingSpeciality = 0;
    public int matchingSkills = 0;
    public FailReason failReason = null;

    public enum FailReason {
        TIMEOUT,
        IMPOSSIBLE
    }
}
