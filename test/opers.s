# Code file created by Pascal2016 compiler 2016-12-04 21:46:57
        .globl _main                         
        .globl main                         
_main:
main:
        call    prog$operatortest_1     
        movl    $0, %eax                
        ret                             
proc$test_3:
        enter   $32, $3                 
        movl    $110,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $111,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $116,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        xorl    $1, %eax                
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        leave                           
        ret                             
proc$testunaryboolean_2:
        enter   $32, $2                 
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    proc$test               
        addl    $4,%esp                 
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    proc$test               
        addl    $4,%esp                 
        leave                           
        ret                             
proc$test_5:
        enter   $32, $3                 
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $97,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $110,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $100,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    %eax, %ecx              
        popl    %eax                    
        andl    %ecx, %eax              
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $111,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $114,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    %eax, %ecx              
        popl    %eax                    
        orl     %ecx, %eax              
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        leave                           
        ret                             
proc$testbinaryboolean_4:
        enter   $32, $2                 
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        leave                           
        ret                             
proc$test_7:
        enter   $32, $3                 
        movl    $45,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        negl    %eax                    
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    $43,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        leave                           
        ret                             
proc$testunarynumeric_6:
        enter   $32, $2                 
        movl    $17,%eax                
        pushl   %eax                    
        call    proc$test               
        addl    $4,%esp                 
        movl    $11,%eax                
        negl    %eax                    
        pushl   %eax                    
        call    proc$test               
        addl    $4,%esp                 
        movl    $0,%eax                 
        pushl   %eax                    
        call    proc$test               
        addl    $4,%esp                 
        leave                           
        ret                             
proc$test_9:
        enter   $32, $3                 
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $43,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $45,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    %eax, %ecx              
        popl    %eax                    
        subl    %ecx, %eax              
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $42,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    %eax, %ecx              
        popl    %eax                    
        imull   %ecx, %eax              
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    $0,%eax                 
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setne   %al                     
        cmpl    $0, %eax                
        je      .L0010                  
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $100,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $105,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $118,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $109,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $111,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $100,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $61,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx, %eax              
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    0(%ebp),%edx            
        movl    0(%edx),%eax            
        pushl   %eax                    
        call    write_bool              
        addl    $4, %esp                
.L0010:
        leave                           
        ret                             
proc$testbinarynumeric_8:
        enter   $32, $2                 
        movl    $17,%eax                
        pushl   %eax                    
        movl    $17,%eax                
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    $11,%eax                
        negl    %eax                    
        pushl   %eax                    
        movl    $17,%eax                
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    $0,%eax                 
        pushl   %eax                    
        movl    $17,%eax                
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    $17,%eax                
        pushl   %eax                    
        movl    $11,%eax                
        negl    %eax                    
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    $11,%eax                
        negl    %eax                    
        pushl   %eax                    
        movl    $11,%eax                
        negl    %eax                    
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    $0,%eax                 
        pushl   %eax                    
        movl    $17,%eax                
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    $17,%eax                
        pushl   %eax                    
        movl    $0,%eax                 
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    $11,%eax                
        negl    %eax                    
        pushl   %eax                    
        movl    $0,%eax                 
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        movl    $0,%eax                 
        pushl   %eax                    
        movl    $0,%eax                 
        pushl   %eax                    
        call    proc$test               
        addl    $8,%esp                 
        leave                           
        ret                             
prog$operatortest_1:
        enter   $32, $1                 
        call    proc$testunaryboolean   
        call    proc$testunarynumeric   
        call    proc$testbinaryboolean  
        call    proc$testbinarynumeric  
        leave                           
        ret                             
