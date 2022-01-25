import { Pipe, PipeTransform } from '@angular/core';

/**
 * A Pipe is also just a class, but this time our class is decorated with the "Pipe" decorator. Notice that the decorator specifies a "name" for your pipe; this name is used in the template when using the pipe (e.g. "| customPipe" ).
 */
@Pipe({
  name: 'customPipe'
})
export class CustomPipePipe implements PipeTransform {

  /**
   * This method "transform" must be overridden as it is an abstract method that is inherited from the PipeTransform interface.
   */
  transform(value: unknown, ...args: unknown[]): unknown {
    
    let moddedString = value as string //We're casting our parameter as a String

    /**
     * Note that this method MUST return the transformed value.
     */
    return moddedString.concat(' plus a transformation');
  }

}
