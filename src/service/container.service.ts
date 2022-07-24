export interface ContainerService {
  buildImage(folder: string, imageName: string);

  runContainer(imageName: string, timeOut: number): string; //Process Output

  stopContainer(containerName: string);

  execCommand(containerName: string, command: string[]): string;

  getRunningContainer();

  getImages();

  deleteImage(iamgeName: string);
}
