# Burrows-Wheeler
My implementation for Burrows Wheeler algorithms for data compression. Programming Assignment 5/ Algorithms Part 2, Princeton.
Language used: JAVA.
Burrows Wheeler's algorithm is one of the most important algorithms for data compression.

The Burrows–Wheeler data compression algorithm consists of three algorithmic components, which are applied in succession:
1.Burrows–Wheeler transform: given a typical English text file, transform it into a text file in which sequences of the same character occur near each other many times.
2.Move-to-front encoding: given a text file in which sequences of the same character occur near each other many times, convert it into a text file in which certain characters appear much more frequently than others.
3.Huffman compression: given a text file in which certain characters appear much more frequently than others, compress it by encoding frequently occurring characters with short codewords and infrequently occurring characters with long codewords.

As it is a loss-less compression, the compressed data can be expanded to get the original data again by performing the last three steps in reverse order.
