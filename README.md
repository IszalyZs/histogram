# Histogram

## Story

A histogram is an approximate, graphical representation of the distribution of numerical
or categorical data. Simply, it represents data using bars of different heights.
In a histogram, each bar group numbers into ranges. Taller bars show that more data falls
in that range. A histogram displays the shape and spread of continuous sample data.
To construct it, the first step is to get the range of values and then counting it occurrence
in your data.

For example, let's take below text and make histogram:

One thousand years ago this story starts.
There were four sorcerers with strong and kind hearts.

```
1  - 3 | ***
4  - 6 | ***********
7  - 9 | **
```

See the above, there are 3 words in the range 1-3 letters count,
11 words in range 4-6, and 2 in the range 7  - 9. The punctuation signs are not included.

I need to ensure, that program which is already implemented will collect all data into histogram
correctly.                
