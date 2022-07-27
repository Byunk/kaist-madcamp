import { Injectable } from '@nestjs/common';
import { ImagesDto } from 'src/images/dto/images.dto';
import { Execution } from 'src/compilers/executes/Execution';
import { v1 } from 'uuid';
import { CompilerService } from './compiler.service';
import { ContainerServiceDefault } from './container.service.default';
import { ImagesService } from '../images/images.service';
import { DockerImage } from 'src/images/entities/dockerimage.entity';
import { Tag } from 'src/images/entities/tag.entity';
import { TagsService } from 'src/images/tags.service';

@Injectable()
export class CompilerServiceDefault implements CompilerService {
  TIME_OUT = 20000;

  containerService: ContainerServiceDefault;

  constructor(
    containerService: ContainerServiceDefault,
    private imagesService: ImagesService,
    private tagsService: TagsService,
  ) {
    this.containerService = containerService;
    this.imagesService = imagesService;
    this.tagsService = tagsService;
  }

  async compile(execution: Execution) {
    // Build Image
    if (
      execution.imageId == null ||
      this.imagesService.find(execution.imageId) == null
    ) {
      execution.imageId = v1().toString();
      execution.tagId = 'latest';
      console.log(
        'No Image Found. Build new image named: ' + execution.imageId,
      );
      await this.builderImage(execution);
    }

    // Run code
    if (
      execution.tagId == null ||
      (await this.imagesService.containsTag(execution.imageId, execution.tagId))
    ) {
      execution.tagId = v1().toString();
    }

    await this.runCode(
      execution.imageId,
      execution.tagId,
      execution.containerId,
    );
    /////////
  }

  private async builderImage(execution: Execution) {
    execution.createExecutionDirectory();
    console.log(
      'Creating execution directory: ' + execution.getExecutionFolderName(),
    );
    console.log('Building the docker image: ' + execution.getImageName());

    try {
      this.containerService.buildImage(execution.path, execution.imageId);
      console.log('Container image has been built');
      const imageDto: DockerImage = new DockerImage();
      imageDto.id = execution.imageId;
      imageDto.language = execution.getLanguage();
      await this.imagesService.create(imageDto);
    } catch (error) {
      // ContainerFailedDependency Exception
      console.warn('Error while building container image: {}', error);
      throw error;
    }
    /*finally {
      try {
        execution.deleteExecutionDirectory();
        console.log('Execution directory has been deleted');
      } catch (error) {
        console.warn(
          'Error while trying to delete execution directory, {}',
          error,
        );
      }
    }*/
  }

  private async runCode(
    imageName: string,
    tagName: string,
    containerName: string,
  ) {
    try {
      this.containerService.runContainer(
        imageName,
        tagName,
        containerName,
        this.TIME_OUT,
      );
      console.log('Run container success!');

      const tag: Tag = new Tag();
      tag.id = tagName;
      console.log('imageName');
      console.log(imageName);
      console.log(typeof imageName);
      tag.dockerImage = await this.imagesService.find(imageName);
      await this.tagsService.saveTag(tag);
    } catch (error) {
      throw error;
    }
  }
}
