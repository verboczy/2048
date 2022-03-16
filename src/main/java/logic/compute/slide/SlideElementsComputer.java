package logic.compute.slide;

import java.util.ArrayList;
import java.util.List;

public class SlideElementsComputer {

    private static final int DEFAULT_VALUE = 0;
    private static final int NO_SCORE = 0;

    private final List<Integer> elements;
    private final List<Integer> mergedAtTheseIndexes;
    private boolean hasChanged = false;

    public SlideElementsComputer(List<Integer> elements) {
        // Create a new ArrayList from elements to make it mutable.
        this.elements = new ArrayList<>(elements);
        this.mergedAtTheseIndexes = new ArrayList<>();
    }

    public SlideResult compute() {
        int score = 0;

        // Index is starting at 1 because it is not necessary to examine the first (0th) element, because it doesn't change, no matter its value.
        for (int indexOfCurrentElement = 1; indexOfCurrentElement < elements.size(); indexOfCurrentElement++) {
            score += computeStepAndReturnScore(indexOfCurrentElement);
        }

        return new SlideResult(elements, score, hasChanged);
    }

    private int computeStepAndReturnScore(final int index) {
        final int currentElement = elements.get(index);
        return currentElement == DEFAULT_VALUE ? NO_SCORE : moveCurrentElementAndReturnScore(index);
    }

    private int moveCurrentElementAndReturnScore(final int index) {
        final int currentElement = elements.get(index);
        final int indexOfPreviousNonZero = getIndexOfPreviousNonZero(elements, index);

        if (wasOnlyDefaultValuesPreviously(indexOfPreviousNonZero)) {
            slideElementToFirstPosition(currentElement, index);
            return NO_SCORE;
        } else {
            return slideElementAndMergeIfPossible(indexOfPreviousNonZero, currentElement, index);
        }
    }

    private int getIndexOfPreviousNonZero(final List<Integer> elements, final int start) {
        for (int indexOfElement = start - 1; indexOfElement >= 0; indexOfElement--) {
            if (elements.get(indexOfElement) != DEFAULT_VALUE) {
                return indexOfElement;
            }
        }
        return -1;
    }

    private boolean wasOnlyDefaultValuesPreviously(final int indexOfPreviousNonZero) {
        return indexOfPreviousNonZero < 0;
    }

    private void slideElementToFirstPosition(final int element, final int fromPosition) {
        slideElementFromPositionToPreviousPosition(element, fromPosition, 0);
    }

    private void slideElementFromPositionToPreviousPosition(final int element, final int fromPosition, final int toPosition) {
        elements.set(fromPosition, DEFAULT_VALUE);
        elements.set(toPosition, element);
        hasChanged = true;
    }

    private int slideElementAndMergeIfPossible(final int indexOfPreviousNonZero, final int currentElement, final int indexOfCurrentElement) {
        final int previousNonNullElement = elements.get(indexOfPreviousNonZero);
        if (canMergePreviousNonNullAndCurrentElement(previousNonNullElement, currentElement, indexOfPreviousNonZero)) {
            return mergePreviousAndCurrentElements(indexOfPreviousNonZero, previousNonNullElement, indexOfCurrentElement, currentElement);
        } else {
            slideElementFromPositionToPositionAfterPreviousNonNull(currentElement, indexOfCurrentElement, indexOfPreviousNonZero);
            return NO_SCORE;
        }
    }

    private boolean canMergePreviousNonNullAndCurrentElement(final int previousNonNullElement, final int currentElement, final int indexOfPreviousNonZero) {
        return previousNonNullElement == currentElement && !mergedAtTheseIndexes.contains(indexOfPreviousNonZero);
    }

    private int mergePreviousAndCurrentElements(
            final int indexOfPreviousNonZero,
            final int previousNonNullElement,
            final int indexOfCurrentElement,
            final int currentElement
    ) {
        final int newElement = previousNonNullElement + currentElement;
        slideElementFromPositionToPreviousPosition(newElement, indexOfCurrentElement, indexOfPreviousNonZero);
        mergedAtTheseIndexes.add(indexOfPreviousNonZero);

        return newElement;
    }

    private void slideElementFromPositionToPositionAfterPreviousNonNull(final int currentElement, final int indexOfCurrentElement, final int indexOfPreviousNonZero) {
        final int indexOfPositionAfterPreviousNonZero = indexOfPreviousNonZero + 1;
        if (indexOfPositionAfterPreviousNonZero != indexOfCurrentElement) {
            slideElementFromPositionToPreviousPosition(currentElement, indexOfCurrentElement, indexOfPositionAfterPreviousNonZero);
        }
    }
}
