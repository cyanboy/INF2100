# Code file created by Pascal2016 compiler 2016-12-04 23:40:31
        .globl _main                         
        .globl main                         
_main:
main:
        call    prog$easter_1           
        movl    $0, %eax                
        ret                             
proc$easter_2:
        enter   $92, $2                 
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            
        pushl   %eax                    
        movl    $19,%eax                
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx, %eax              
        movl    -8(%ebp),%edx           
        movl    %eax,-36(%edx)          
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            
        pushl   %eax                    
        movl    $100,%eax               
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    -8(%ebp),%edx           
        movl    %eax,-40(%edx)          
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            
        pushl   %eax                    
        movl    $100,%eax               
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx, %eax              
        movl    -8(%ebp),%edx           
        movl    %eax,-44(%edx)          
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          
        pushl   %eax                    
        movl    $4,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    -8(%ebp),%edx           
        movl    %eax,-48(%edx)          
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          
        pushl   %eax                    
        movl    $4,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx, %eax              
        movl    -8(%ebp),%edx           
        movl    %eax,-52(%edx)          
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          
        pushl   %eax                    
        movl    $8,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    $25,%eax                
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    -8(%ebp),%edx           
        movl    %eax,-56(%edx)          
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -56(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        subl    %ecx, %eax              
        pushl   %eax                    
        movl    $1,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    $3,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    -8(%ebp),%edx           
        movl    %eax,-60(%edx)          
        movl    $19,%eax                
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        imull   %ecx, %eax              
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -48(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        subl    %ecx, %eax              
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -60(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        subl    %ecx, %eax              
        pushl   %eax                    
        movl    $15,%eax                
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    $30,%eax                
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx, %eax              
        movl    -8(%ebp),%edx           
        movl    %eax,-64(%edx)          
        movl    -8(%ebp),%edx           
        movl    -44(%edx),%eax          
        pushl   %eax                    
        movl    $4,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    -8(%ebp),%edx           
        movl    %eax,-68(%edx)          
        movl    -8(%ebp),%edx           
        movl    -44(%edx),%eax          
        pushl   %eax                    
        movl    $4,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx, %eax              
        movl    -8(%ebp),%edx           
        movl    %eax,-72(%edx)          
        movl    $32,%eax                
        pushl   %eax                    
        movl    $2,%eax                 
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -52(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        imull   %ecx, %eax              
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    $2,%eax                 
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -68(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        imull   %ecx, %eax              
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -64(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        subl    %ecx, %eax              
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -72(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        subl    %ecx, %eax              
        pushl   %eax                    
        movl    $7,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx, %eax              
        movl    -8(%ebp),%edx           
        movl    %eax,-76(%edx)          
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          
        pushl   %eax                    
        movl    $11,%eax                
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -64(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        imull   %ecx, %eax              
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    $22,%eax                
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -76(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        imull   %ecx, %eax              
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    $451,%eax               
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    -8(%ebp),%edx           
        movl    %eax,-80(%edx)          
        movl    -8(%ebp),%edx           
        movl    -64(%edx),%eax          
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -76(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    $7,%eax                 
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -80(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        imull   %ecx, %eax              
        movl    %eax, %ecx              
        popl    %eax                    
        subl    %ecx, %eax              
        pushl   %eax                    
        movl    $114,%eax               
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    $31,%eax                
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    -8(%ebp),%edx           
        movl    %eax,-84(%edx)          
        movl    -8(%ebp),%edx           
        movl    -64(%edx),%eax          
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -76(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    $7,%eax                 
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -80(%edx),%eax          
        movl    %eax, %ecx              
        popl    %eax                    
        imull   %ecx, %eax              
        movl    %eax, %ecx              
        popl    %eax                    
        subl    %ecx, %eax              
        pushl   %eax                    
        movl    $114,%eax               
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        pushl   %eax                    
        movl    $31,%eax                
        movl    %eax, %ecx              
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx, %eax              
        pushl   %eax                    
        movl    $1,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        movl    -8(%ebp),%edx           
        movl    %eax,-88(%edx)          
        movl    -8(%ebp),%edx           
        movl    -84(%edx),%eax          
        pushl   %eax                    
        movl    $3,%eax                 
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        sete    %al                     
        cmpl    $0, %eax                
        je      .L0003                  
        movl    -8(%ebp),%edx           
        movl    -88(%edx),%eax          
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $39,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $77,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $97,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $114,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $99,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $104,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $10,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        jmp     .L0004                  
.L0003:
        movl    -8(%ebp),%edx           
        movl    -88(%edx),%eax          
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $65,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $112,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $114,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $105,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $108,%eax               
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    $32,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            
        pushl   %eax                    
        call    write_int               
        addl    $4, %esp                
        movl    $10,%eax                
        pushl   %eax                    
        call    write_char              
        addl    $4, %esp                
.L0004:
        leave                           
        ret                             
prog$easter_1:
        enter   $36, $1                 
        movl    $2010,%eax              
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          
.L0005:
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          
        pushl   %eax                    
        movl    $2020,%eax              
        popl    %ecx                    
        cmpl    %eax, %ecx              
        movl    $0, %eax                
        setle   %al                     
        cmpl    $0, %eax                
        je      .L0006                  
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          
        pushl   %eax                    
        call    proc$easter             
        addl    $4,%esp                 
        movl    -4(%ebp),%edx           
        movl    -36(%edx),%eax          
        pushl   %eax                    
        movl    $1,%eax                 
        movl    %eax, %ecx              
        popl    %eax                    
        addl    %ecx, %eax              
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          
        jmp     .L0005                  
.L0006:
        leave                           
        ret                             
