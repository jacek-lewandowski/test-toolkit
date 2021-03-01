package net.enigma.test.toolkit;

class HeapAnalyzerAgent
{
    public static native void init();

    /**
     * Traverse heap over reachable objects, count their number and size.
     * Size and counts are reported for all reachable objects and for reachable objects which are marked.
     *
     * @return heap traversal summary
     * @see #markObject(Object)
     */
    public static native HeapTraversalSummary traverseHeap();

    /**
     * Traverse heap over reachable objects, count their number and size.
     * Size and counts are reported for all objects reachable from the given object and separately for those which are marked.
     *
     * @return heap traversal summary
     * @see #markObject(Object)
     */
    public static native HeapTraversalSummary traverseHeapFrom(Object object);

    /**
     * Force full garbage collection
     */
    public static native void forceGC();

    /**
     * Set the tag on object
     *
     * @param object object
     * @param tag tag, where 0 is considered as no-tag
     */
    public static native void setTag(Object object, long tag);

    /**
     * Retrieves object tag
     *
     * @param object object
     *
     * @return tag, where 0 is considered as no-tag
     */
    public static native long getTag(Object object);

    /**
     * Sets the tag {@code newTag} on all objects tagged with {@code curTag}
     * given {@code curTag} is != 0; or on all objects on heap otherwise.
     *
     * @param curTag tag of the objects to be selected or 0 if all objects are to be selected
     * @param newTag new tag to be set
     */
    public static native void setTag(long curTag, long newTag);

    /**
     * Set a marker flag on the given object's tag.
     */
    public static native void markObject(Object obj);

    /**
     * Set a marker flag on the given object's tag and save its explicit size.
     * The explicit size will be used for calculating heap memory usage of marked objects
     * instead of the real object's size. This can be useful if we want to stress that 
     * only a part of the object data is used (for example, a slice of a shared byte array).
     */
    public static native void markObject(Object obj, int explicitSize);

    /**
     * Remove marker flag from the given object's tag.
     */
    public static native void unmarkObject(Object obj);

    /**
     * Mark classes whose signature matches the given substring so that references from objects of those classes
     * will be skipped during reference traversals
     */
    public static native void skipRefsFromClassesBySubstring(String pattern);

    /**
     * Go through the references to reachable objects on the heap. Follow the references leading to those live objects
     * which are marked and print those reference paths to stderr.
     *
     * @param sizeThreshold reference paths to objects with size equal or higher will be considered
     */
    public static native void dumpReferences(long sizeThreshold, int depth);

}
