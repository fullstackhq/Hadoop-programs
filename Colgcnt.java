package org.sparkexample;
import java.util.Arrays;
import java.util.List;
import java.lang.Iterable;

import org.apache.hadoop.io.IntWritable;
import scala.Tuple2;

import org.apache.commons.lang.StringUtils;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;


public class WordCount {
    public static void main(String[] args) throws Exception {
        String inputFile = args[0];
        //String inputFil1 = args[1];
        String outputFile = args[1];

        SparkConf conf = new SparkConf().setAppName("wordCount");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> input = sc.textFile(inputFile);
        //JavaRDD<String> input1 = sc.textFile(inputFil1);

        JavaRDD<String> words = input.flatMap(
                                new FlatMapFunction<String,String> {
                                  public Iterable<String> call(String x){
                                    return Arrays.asList(x.split(" "));
                                  }
                                }
        )

        words.saveAsTextFile(outputFile);


    }
}
