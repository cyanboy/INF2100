# Code file created by Pascal2016 compiler 2016-12-04 23:36:56
        .globl _main                         
        .globl main                         
_main:
main:
        call    prog$gcd_1              
        movl    $0, %eax                
        ret                             
func$gcd_2:
        enter   $32, $2                 
        movl    -8(%ebp),%edx           
        movl    12(%edx),%eax           
        pushl   %eax                    
        movl    $0,%eax                 
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        sete    %al                     
        cmpl    $0, %eax                
        je      .L0003                  
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            
        movl    -8(%ebp), %edx          
        movl    %eax, -32(%edx)         
        jmp     .L0004                  
.L0003:
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    12(%edx),%eax           
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx, %eax              
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    12(%edx),%eax           
        pushl   %eax                    
        call    func$gcd                
        addl    $8,%esp                 
        movl    -8(%ebp), %edx          
        movl    %eax, -32(%edx)         
.L0004:
        movl    -32(%ebp), %eax         
        leave                           
        ret                             
prog$gcd_1:
        enter   $36, $1                 
        movl    $462,%eax               
        pushl   %eax                    
        movl    $1071,%eax              
        pushl   %eax                    
        call    func$gcd                
        addl    $8,%esp                 
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          
        movl    $71,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $67,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $68,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $40,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $1071,%eax              
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $44,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $462,%eax               
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $41,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $10,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        leave                           
        ret                             
