import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';

export type ImagesDocument = Images & Document;

@Schema()
export class Images {
  @Prop({ required: true })
  id: string;

  @Prop()
  language: string;

  @Prop([String])
  tags: string[];
}

export const ImagesSchema = SchemaFactory.createForClass(Images);
