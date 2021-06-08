package models;

import java.util.Arrays;

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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if(failReason != null)
            switch (failReason) {
                case TIMEOUT:
                    stringBuilder.append("{ FailReason: TIMEOUT }");
                    break;
                case IMPOSSIBLE:
                    stringBuilder.append("{ FailReason: IMPOSSIBLE }");
                    break;
            }
        else
        {
            stringBuilder.append("{ affectations: ");
            stringBuilder.append(Arrays.toString(affectations));
            stringBuilder.append(",\ninterfacesHours: ");
            stringBuilder.append(Arrays.toString(interfacesHours));
            stringBuilder.append(",\nmatchingSpeciality: ");
            stringBuilder.append(matchingSpeciality);
            stringBuilder.append(",\nmatchingSkills: ");
            stringBuilder.append(matchingSkills);
            stringBuilder.append(",\ncost: ");
            stringBuilder.append(cost);
            stringBuilder.append(" }");
        }

        return stringBuilder.toString();
    }
}
