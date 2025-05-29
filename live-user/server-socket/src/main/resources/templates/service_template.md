# class level

@Service

# method level

    @Autowired
    private Repository Repo;

    @Autowired
    private DateTimeFormatterService dtfs;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

# INFO

logger.log(Level.INFO, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");

# ERROR

logger.log(Level.SEVERE, "\n\n -> [ Error Class: " + e.getClass() + " || Message: " + e.getMessage() + " || Origin Class: " + this.getClass().getName() + " ] ");
e.printStackTrace();

HttpStatus