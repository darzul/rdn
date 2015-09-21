#include <stdio.h>
#include "fann.h"

int main(int argc, char *argv[])
{
    if(argc != 3) {
        printf("Bad args: data_file net_file_saved\n");
        return 1;
    }
    
    unsigned int num_input = 26;
    unsigned int num_output = 1;
    const unsigned int num_layers = 3;
    const unsigned int num_neurons_hidden = 3;
    const float desired_error = (const float) 0.001;
    const unsigned int max_epochs = 10000;
    const unsigned int epochs_between_reports = 1000;
    
    FILE *file = fopen(argv[1], "r");
    int num_data;
    fscanf(file, "%d %d %d", &num_data, &num_input, &num_output);
    fclose(file);
	
    struct fann *ann = fann_create_standard(num_layers, num_input, num_neurons_hidden, num_output);
	
    fann_set_activation_function_hidden(ann, FANN_SIGMOID_SYMMETRIC);
    fann_set_activation_function_output(ann, FANN_SIGMOID_SYMMETRIC);
	
    fann_train_on_file(ann, argv[1], max_epochs, epochs_between_reports, desired_error);
	
    fann_save(ann, argv[2]);
	
    fann_destroy(ann);
	
    return 0;
}
