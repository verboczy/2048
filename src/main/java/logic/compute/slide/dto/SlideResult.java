package logic.compute.slide.dto;

import java.util.List;
import java.util.Objects;

public class SlideResult {
    private final List<Integer> elementsAfterSlide;
    private final int scoreOfSlide;
    private final boolean hasChanged;

    public SlideResult(List<Integer> elementsAfterSlide, int scoreOfSlide, boolean hasChanged) {
        this.elementsAfterSlide = elementsAfterSlide;
        this.scoreOfSlide = scoreOfSlide;
        this.hasChanged = hasChanged;
    }

    public List<Integer> getElementsAfterSlide() {
        return elementsAfterSlide;
    }

    public int getScoreOfSlide() {
        return scoreOfSlide;
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlideResult that = (SlideResult) o;
        return scoreOfSlide == that.scoreOfSlide && elementsAfterSlide.equals(that.elementsAfterSlide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementsAfterSlide, scoreOfSlide);
    }

    @Override
    public String toString() {
        return "SlideResult{" +
                "elementsAfterSlide=" + elementsAfterSlide +
                ", scoreOfSlide=" + scoreOfSlide +
                '}';
    }
}
