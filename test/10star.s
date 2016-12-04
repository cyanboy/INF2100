# Code file created by Pascal2016 compiler 2016-12-04 23:37:47
        .globl _main                         
        .globl main                         
_main:
main:
        call    prog$tenstars_1         
        movl    $0, %eax                
        ret                             
prog$tenstars_1:
        enter   $36, $1                 
        movl    $0,%eax                 
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          
.L0002:
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          
        pushl   %eax                    
        movl    $10,%eax                
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setl    %al                     
        cmpl    $0, %eax                
        je      .L0003                  
        movl    $42,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          
        pushl   %eax                    
        movl    $1,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          
        jmp     .L0002                  
.L0003:
        movl    $10,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        leave                           
        ret                             
