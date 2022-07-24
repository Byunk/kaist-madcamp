export interface ContainerService {
  buildImage(folder: string, imageName: string);

  runContainer(imageName: string, timeOut: number); //Process Output

  getRunningContainer();

  getImages();

  deleteImage(iamgeName: string);
}
