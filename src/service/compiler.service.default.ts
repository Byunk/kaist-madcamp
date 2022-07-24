import { Injectable } from '@nestjs/common';
import { CompilerController } from 'src/controller/compiler.controller';
import { Execution } from 'src/executes/Execution';
import { CompilerService } from './compiler.service';
import { ContainerService } from './container.service';
import { ContainerServiceDefault } from './container.service.default';

@Injectable()
export class CompilerServiceDefault implements CompilerService {
  TIME_OUT = 20000;

  containerService: ContainerServiceDefault;

  constructor(containerService: ContainerServiceDefault) {
    this.containerService = containerService;
  }

  compile(execution: Execution) {
    // Build Image
    this.builderImage(execution);

    // Run code
    this.runCode(execution.getImageName());

    // Delete Image & Get output
    //this.containerService.deleteImage(execution.getImageName());

    /////////
  }

  private builderImage(execution: Execution) {
    try {
      console.log(
        'Creating execution directory: ' + execution.getExecutionFolderName(),
      );
      execution.createExecutionDirectory();
    } catch (error) {
      throw error;
    }

    try {
      console.log('Building the docker image: ' + execution.getImageName());
      try {
        this.containerService.buildImage(
          execution.path,
          execution.getImageName(),
        );
        console.log('Container image has been built');
      } catch (error) {
        // ContainerFailedDependency Exception
        console.warn('Error while building container image: {}', error);
        throw error;
      }
    } catch {
      try {
        //execution.deleteExecutionDirectory();
        console.log(
          'Execution directory {} has been deleted',
          execution.getExecutionFolderName(),
        );
      } catch (error) {
        console.warn(
          'Error while trying to delete execution directory, {}',
          error,
        );
      }
    }
  }

  private runCode(imageName: string) {
    this.containerService.runContainer(imageName, this.TIME_OUT);
  }
}
