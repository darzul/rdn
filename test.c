#include <stdio.h>
#include "floatfann.h"
	
int main(int argc, char *argv[])
{   
    if(argc != 4) {
        printf("Bad args: net_file data_file result_file\n");
        return 1;
    }
    
    fann_type *calc_out;
	
    struct fann *ann = fann_create_from_file(argv[1]);
    
    int num_data;
    int num_input;
    int num_output;
	FILE *data_file = fopen(argv[2], "r");
    fscanf(data_file, "%d %d %d", &num_data, &num_input, &num_output);
    fann_type input[num_input];
    
    FILE *result_file = fopen(argv[3], "w+");
    
    double current_input;
    double current_output;
    int line = 0;
    for(; line < num_data; line++) {
    
        int pos_input = 0;
        for(; pos_input < num_input; pos_input++) {
            fscanf(data_file, "%lf", &current_input);
            input[pos_input] = current_input;
        }
        
        int pos_output = 0;
        for(; pos_output < num_output; pos_output++) {
            fscanf(data_file, "\n%lf", &current_output);
            fprintf(result_file, "%lf ", current_output);
        }
        fprintf(result_file, "\n");
        
        calc_out = fann_run(ann, input);
        for(pos_output = 0; pos_output < num_output; pos_output++) {
            fprintf(result_file, "%lf ", calc_out[pos_output]);
        }
        fprintf(result_file, "\n");
    }

    fclose(result_file);
    fclose(data_file);
    fann_destroy(ann);
    
    return 0;
}
